package me.remind.rest.sandbox.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.remind.rest.sandbox.model.User;
import me.remind.rest.sandbox.repository.UserRepository;
import me.remind.rest.sandbox.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Service implementation for the User entity
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Create User
     *
     * @return id
     * @para user
     */
    public UUID saveUser(User user) {

        user.setGitHubId(extractGitHubId(user.getGitHubURL()));
        user = userRepository.save(user);
        return user.getId();
    }

    /**
     * GET User
     *
     * @return user
     * @para id
     */
    public User getUserById(UUID id) {
        User user = userRepository.findOne(id);
        return user;
    }

    /**
     * update User
     *
     * @para user
     */
    @Override
    public void updateUser(User user) {

        user.setGitHubId(extractGitHubId(user.getGitHubURL()));
        userRepository.save(user);
    }

    /**
     * geall User
     */
    @Override
    public List<User> getAlluser() {
        List<User> users = (List<User>) userRepository.findAll();
        return users;
    }

    /**
     * delete User
     *
     * @para id
     */
    @Override
    public void deleteUserbyId(UUID id) {
        userRepository.delete(id);
    }

    /**
     * check for exist User
     *
     * @return exist
     * @para id
     */
    public boolean userExist(UUID id) {
        boolean exist = userRepository.exists(id);
        return exist;
    }

    /**
     * extract Github Id
     *
     * @return id
     * @para url
     */
    @Override
    //Example URL: https://github.com/ThomasMaier
    public String extractGitHubId(String url) {

        String[] gitHubId = url.split("/");
        return gitHubId[3];
    }

}
