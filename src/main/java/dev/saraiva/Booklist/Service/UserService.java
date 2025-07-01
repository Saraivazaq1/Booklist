package dev.saraiva.Booklist.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.saraiva.Booklist.Model.UserModel;
import dev.saraiva.Booklist.Repository.UserRepository;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Create user
    public UserModel createUser(UserModel userModel) {
        return userRepository.save(userModel);
    }

    // Get user
    public List<UserModel> getUser() {
        return userRepository.findAll();
    }

    // Get user by ID
    public UserModel getUserById(Long id) {
        Optional<UserModel> user = userRepository.findById(id);
        return user.orElse(null);
    }

    // Delete user by ID
    public void deleteUserByID(Long id) {
        userRepository.deleteById(id);
    }

    // Update user by ID
    public UserModel updateUserById(UserModel userModel, Long id) {
        if (userRepository.existsById(id)) {
            userModel.setId(id);
            return userRepository.save(userModel);
        } else {
            return null;
        }
    }
}
