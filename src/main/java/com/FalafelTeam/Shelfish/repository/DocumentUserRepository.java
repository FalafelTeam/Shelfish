package com.FalafelTeam.Shelfish.repository;

import com.FalafelTeam.Shelfish.model.DocumentUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * interface for the DocumentUserRepository
 */
@Repository
public interface DocumentUserRepository extends CrudRepository<DocumentUser, Long> {
}
