package com.FalafelTeam.Shelfish.repository;

import com.FalafelTeam.Shelfish.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findUserByLoginAndPassword(String login, String password);
}
