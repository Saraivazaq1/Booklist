package dev.saraiva.Booklist.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.saraiva.Booklist.Model.UserModel;
import dev.saraiva.Booklist.Service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Create user
    @PostMapping("/create")
    public UserModel createUser(@RequestBody UserModel userModel) {
        return userService.createUser(userModel);
    }

    // Get user
    @GetMapping("/get")
    public List<UserModel> getUser() {
        return userService.getUser();
    }

    // Get user by ID
    @GetMapping("/get/{id}")
    public UserModel getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    // Delete user by ID
    @DeleteMapping("/delete/{id}")
    public String deleteUserById(@PathVariable Long id) {
        userService.deleteUserByID(id);
        return "User deleted";
    }

    // Update user by ID
    @PutMapping("/update/{id}")
    public UserModel updateUserByID(@PathVariable Long id, @RequestBody UserModel userModel) {
        return userService.updateUserById(userModel, id);
    }
}
