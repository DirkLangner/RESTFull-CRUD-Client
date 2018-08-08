package me.remind.rest.sandbox.service;

import me.remind.rest.sandbox.model.User;

import java.util.List;
import java.util.UUID;

/**
 * UserService
 */
public interface UserService {

    UUID saveUser(User user);

    void updateUser(User user);

    List<User> getAlluser();

    User getUserById(UUID id);

    void deleteUserbyId(UUID id);

    boolean userExist(UUID id);

    String extractGitHubId(String url);
}
