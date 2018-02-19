package com.FalafelTeam.Shelfish.repository;

import com.FalafelTeam.Shelfish.model.Document;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * interface for the DocumentRepository
 */
@Transactional
public interface DocumentRepository extends CrudRepository<Document, Integer> {
}
