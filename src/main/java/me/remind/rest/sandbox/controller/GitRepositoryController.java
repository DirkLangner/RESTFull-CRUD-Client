package me.remind.rest.sandbox.controller;

import lombok.extern.slf4j.Slf4j;
import me.remind.rest.sandbox.dto.UserDTO;
import me.remind.rest.sandbox.model.User;
import me.remind.rest.sandbox.service.RestClientService;
import me.remind.rest.sandbox.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * RESTController to handle requests to the GitHub REST API v3
 */
@Slf4j
@RestController
@RequestMapping("/repositories")
public class GitRepositoryController {

    @Autowired
    private RestClientService restClientService;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * checks the user github repositories
     *
     * @param id
     * @return ResponseEntity<?>
     */
    @GetMapping("user/{id}")
    public ResponseEntity<?> check(@PathVariable UUID id) {

        log.info("starting check method");
        if (!userService.userExist(id)) {
            return ResponseEntity.notFound().build();
        } else {
            User user = userService.getUserById(id);
            UserDTO userDTO = modelMapper.map(user, UserDTO.class);
            userDTO.getGitHubURL();
            restClientService.restClient();
        }
        return ResponseEntity.ok().body("Your welcome");

    }
}