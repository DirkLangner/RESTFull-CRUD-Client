package me.remind.rest.sandbox.repository;

import me.remind.rest.sandbox.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

/**
 * Spring CRUD repository for the User entity.
 */
public interface UserRepository extends CrudRepository<User, UUID> {
}
