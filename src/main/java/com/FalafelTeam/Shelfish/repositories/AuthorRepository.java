package com.FalafelTeam.Shelfish.repositories;

import com.FalafelTeam.Shelfish.model.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}
