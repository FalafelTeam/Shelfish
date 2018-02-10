package com.FalafelTeam.Shelfish.repository;

import com.FalafelTeam.Shelfish.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * interface for the UserRepository
 * an actual repository is automatically created by spring
 */
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    User findUserByLoginAndPassword(String login, String password);
}
