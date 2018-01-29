package com.FalafelTeam.Shelfish.repositories;

import com.FalafelTeam.Shelfish.model.Document;
import org.springframework.data.repository.CrudRepository;

public interface DocumentRepository extends CrudRepository<Document, Long> {
}
