package com.example.springrest;

import com.example.springrest.entity.User;
import com.example.springrest.exceptions.UserNotFoundException;
import com.example.springrest.service.RepositoryStubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserRestController {


    @Autowired
    private RepositoryStubService repositoryService;

    @GetMapping
    public List<User> listAllUsers() {
        return repositoryService.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        User user = repositoryService.findUserById(id);
        if (user == null) {
            throw new UserNotFoundException(id);
        }
        return user;
    }

    @PostMapping
    public User createUser(@RequestBody User newUser) {
        return repositoryService.saveUser(newUser);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        User userToUpdate = repositoryService.findUserById(id);
        if (userToUpdate != null) {
            userToUpdate.setFirstName(updatedUser.getFirstName());
            userToUpdate.setSecondName(updatedUser.getSecondName());
            userToUpdate.setPosition(updatedUser.getPosition());
            userToUpdate.setDepartment(updatedUser.getDepartment());
            return repositoryService.saveUser(id, userToUpdate);
        } else {
            updatedUser.setId(id);
            return repositoryService.saveUser(id, updatedUser);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        User userToDelete = repositoryService.findUserById(id);
        if (userToDelete != null){
            repositoryService.deleteById(id);
        } else {
            throw new UserNotFoundException(id);
        }
    }
}
