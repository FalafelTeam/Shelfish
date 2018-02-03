package com.FalafelTeam.Shelfish.repositories;

import com.FalafelTeam.Shelfish.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
