package com.FalafelTeam.Shelfish.repository;

import com.FalafelTeam.Shelfish.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * interface for the UserRepository
 * an actual repository is automatically created by spring
 */
@Transactional
public interface UserRepository extends CrudRepository<User, Integer> {

    User findById(int id);

    User findByName(String name);

    User findUserByLoginAndPassword(String login, String password);

    List<User> findAllByType(String type);

    List<User> findAllByTypeOrType(String type1, String type2);

    void deleteById(int id);
}
