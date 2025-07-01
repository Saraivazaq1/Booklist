package dev.saraiva.Booklist.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import dev.saraiva.Booklist.DTO.UserRequestDTO;
import dev.saraiva.Booklist.DTO.UserResponseDTO;
import dev.saraiva.Booklist.Mapper.UserMapper;
import dev.saraiva.Booklist.Model.UserModel;
import dev.saraiva.Booklist.Repository.UserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    // Create user
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        UserModel userModel = userMapper.map(userRequestDTO);
        userModel = userRepository.save(userModel);
        return userMapper.map(userModel);
    }

    // Get user
    public List<UserResponseDTO> getUser() {
        List<UserModel> users = userRepository.findAll();
        return users.stream()
            .map(userMapper::map)
            .collect(Collectors.toList());
    }

    // Get user by ID
    public UserResponseDTO getUserById(Long id) {
        Optional<UserModel> user = userRepository.findById(id);
        return user.map(userMapper::map).orElse(null);
    }

    // Delete user by ID
    public void deleteUserByID(Long id) {
        userRepository.deleteById(id);
    }

    // Update user by ID
    public UserResponseDTO updateUserById(UserRequestDTO userRequestDTO, Long id) {
        Optional<UserModel> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            UserModel userModelExistent = userOptional.get();
            UserModel userModelNew = userMapper.map(userRequestDTO);
            userModelNew.setId(userModelExistent.getId());
            userModelNew = userRepository.save(userModelNew);
            return userMapper.map(userModelNew);
        }
        return null;
    }
}
