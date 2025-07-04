package dev.saraiva.Booklist.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.saraiva.Booklist.DTO.UserRequestDTO;
import dev.saraiva.Booklist.DTO.UserResponseDTO;
import dev.saraiva.Booklist.Model.BookModel;
import dev.saraiva.Booklist.Service.BookService;
import dev.saraiva.Booklist.Service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    // Create user
    @PostMapping("/create")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO userRequestDTO) {
        UserResponseDTO created = userService.createUser(userRequestDTO);
        return ResponseEntity.ok(created);
    }

    // Get user
    @GetMapping("/get")
    public ResponseEntity<List<UserResponseDTO>> getUser() {
        return ResponseEntity.ok(userService.getUser());
    }

    // Get user by ID
    @GetMapping("/get/{id}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    // Delete user by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id) {
        userService.deleteUserByID(id);
        return ResponseEntity.ok("User Deleted");
    }

    // Update user by ID
    @PutMapping("/update/{id}")
    public ResponseEntity<UserResponseDTO> updateUserByID(@PathVariable Long id, @RequestBody UserRequestDTO userRequestDTO) {
        UserResponseDTO userResponseDTO = userService.updateUserById(userRequestDTO, id);
        return ResponseEntity.ok(userResponseDTO);
    }

    @PutMapping("/addBook/{id}/{title}")
    public ResponseEntity<UserResponseDTO> addBook(@PathVariable Long id, @PathVariable String title, @RequestBody UserRequestDTO userRequestDTO) {
        UserResponseDTO userResponseDTO = bookService.addBook(id, title, userRequestDTO);
        return ResponseEntity.ok(userResponseDTO);
    }

    @PutMapping("/deleteBook/{id}/{title}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id, @PathVariable String title) {
        bookService.deleteBook(id, title);
        return ResponseEntity.ok("Book deleted");
    }

    @GetMapping("/getBook/{id}")
    public ResponseEntity<List<BookModel>> getBook(@PathVariable Long id) {
        var books = bookService.getBook(id);
        return ResponseEntity.ok(books);
    }
}
