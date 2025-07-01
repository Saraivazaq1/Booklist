package dev.saraiva.Booklist.Mapper;

import org.springframework.stereotype.Component;

import dev.saraiva.Booklist.DTO.UserRequestDTO;
import dev.saraiva.Booklist.DTO.UserResponseDTO;
import dev.saraiva.Booklist.Model.UserModel;

@Component
public class UserMapper {

    public UserModel map(UserRequestDTO dto) {
        UserModel userModel = new UserModel();
        userModel.setName(dto.getName());
        userModel.setEmail(dto.getEmail());
        userModel.setBooks(dto.getBooks());
        return userModel;
    }

    public UserResponseDTO map(UserModel model) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(model.getId());
        userResponseDTO.setName(model.getName());
        userResponseDTO.setEmail(model.getEmail());
        userResponseDTO.setBooks(model.getBooks());
        return userResponseDTO;
    }
}
