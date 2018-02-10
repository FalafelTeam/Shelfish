package com.FalafelTeam.Shelfish.repository;

import com.FalafelTeam.Shelfish.model.Document;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * interface for the DocumentRepository
 */
@Repository
public interface DocumentRepository extends CrudRepository<Document, Integer> {
}
