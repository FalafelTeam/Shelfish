package com.FalafelTeam.Shelfish.repository;

import com.FalafelTeam.Shelfish.model.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * interface for the AuthorRepository
 */
@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {
}
