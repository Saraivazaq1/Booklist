package dev.saraiva.Booklist.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dev.saraiva.Booklist.Client.GoogleBooksApiClient;
import dev.saraiva.Booklist.DTO.UserRequestDTO;
import dev.saraiva.Booklist.DTO.UserResponseDTO;
import dev.saraiva.Booklist.Mapper.UserMapper;
import dev.saraiva.Booklist.Model.BookModel;
import dev.saraiva.Booklist.Model.UserModel;
import dev.saraiva.Booklist.Repository.UserRepository;
import lombok.Data;
import lombok.experimental.var;

@Service
@Data
public class BookService {

    @Autowired
    private GoogleBooksApiClient googleBooksApiClient;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public UserResponseDTO addBook(Long id, String title, UserRequestDTO userRequestDTO) {

        Optional<UserModel> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            UserModel userModelExistent = userOptional.get();
            UserModel userModelNew = userMapper.map(userRequestDTO);
            BookModel bookModel = googleBooksApiClient.searchBook(title);
            List<BookModel> books = new ArrayList<>();

            userModelNew.setId(userModelExistent.getId());
            if (userModelExistent.getBooks() != null) {
                books.addAll(userModelExistent.getBooks());
            }

            books.add(bookModel);
            userModelNew.setBooks(books);
            userRepository.save(userModelNew);
            return userMapper.map(userModelNew);
        }
        return null;
    }

    public List<BookModel> getBook(Long userId) {
        Optional<UserModel> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            UserModel user = userOptional.get();
            var books = user.getBooks();
            if (books != null && !books.isEmpty()) {
                return books.stream().collect(Collectors.toList());
            }
        }

        return Collections.emptyList();
    }


    public void deleteBook(Long id, String title) {

        Optional<UserModel> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            UserModel user = userOptional.get();

            List<BookModel> books = user.getBooks();
            if (books != null && !books.isEmpty()) {
                
                for (Iterator<BookModel> iterator = books.iterator(); iterator.hasNext(); ) {
                    BookModel book = iterator.next();
                    if (book.getTitle().contains(title)) {
                        iterator.remove();
                        break;
                    }
                } 

                userRepository.save(user);
            }
        } 

    }
}
