package com.FalafelTeam.Shelfish.repository;

import com.FalafelTeam.Shelfish.model.Document;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * interface for the DocumentRepository
 */
@Transactional
public interface DocumentRepository extends CrudRepository<Document, Integer> {

    Document findById(Integer id);

    List<Document> findAllByType(String type);
}
