package com.FalafelTeam.Shelfish.repository;

import com.FalafelTeam.Shelfish.model.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * interface for the AuthorRepository
 */
@Transactional
public interface AuthorRepository extends CrudRepository<Author, Integer> {

    Author findById(Integer id);

    Author findByName(String name);
}
