package me.remind.rest.sandbox.controller;

import me.remind.rest.sandbox.dto.UserDTO;
import me.remind.rest.sandbox.model.User;
import me.remind.rest.sandbox.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * RESTController to handle CRUD requsts for User
 */
@RestController
@RequestMapping("/userCRUD")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * CREATE a user in the DB
     *
     * @param userDTO containing user
     * @return ResponseEntity
     */
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody UserDTO userDTO) {

        UUID id;

        if (userDTO == null || (userDTO.getFirstName() == null || userDTO.getLastName() == null || userDTO.getGitHubURL() == null)) {
            return ResponseEntity.badRequest().build();
        } else {
            User user;
            user = modelMapper.map(userDTO, User.class);
            id = userService.saveUser(user);
        }
        return ResponseEntity.ok().body("new user has been created with ID: " + id);
    }

    /**
     * GET a user from the DB
     *
     * @param id
     * @return ResponseEntity
     */
    @GetMapping("/user/{id}")
    public ResponseEntity<UserDTO> get(@PathVariable UUID id) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }
        if (!userService.userExist(id)) {
            return ResponseEntity.notFound().build();
        } else {
            User user = userService.getUserById(id);
            UserDTO userDTO = modelMapper.map(user, UserDTO.class);
            return ResponseEntity.ok().body(userDTO);
        }
    }

    /**
     * UPDATE a user in the DB
     *
     * @param id
     * @param userDTO
     * @return ResponseEntity
     */
    @PutMapping("/user/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody UserDTO userDTO) {

        if (!userService.userExist(id)) {
            return ResponseEntity.notFound().build();
        }
        User user = modelMapper.map(userDTO, User.class);
        user.setId(id);
        userService.updateUser(user);
        return ResponseEntity.ok().body("User has been updated successfully.");
    }

    /**
     * DELETE a user in the DB
     *
     * @param id
     * @return ResponseEntity
     */
    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        if (!userService.userExist(id)) {
            return ResponseEntity.notFound().build();
        }
        userService.deleteUserbyId(id);
        return ResponseEntity.ok().body("User has been deleted successfully.");
    }

    /**
     * list all User
     *
     * @return ResponseEntity
     */
    @GetMapping("/list")
    public ResponseEntity<List<UserDTO>> list() {
        List<User> users = userService.getAlluser();
        java.lang.reflect.Type targetListType = new TypeToken<List<UserDTO>>() {
        }.getType();
        List<UserDTO> usersDTOs = modelMapper.map(users, targetListType);
        return ResponseEntity.ok().body(usersDTOs);
    }
}
