package com.FalafelTeam.Shelfish.repository;

import com.FalafelTeam.Shelfish.model.Document;
import com.FalafelTeam.Shelfish.model.DocumentUser;
import com.FalafelTeam.Shelfish.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * interface for the DocumentUserRepository
 */
@Transactional
public interface DocumentUserRepository extends CrudRepository<DocumentUser, Integer> {

    DocumentUser findByUserAndDocument(User user, Document document);

    List<DocumentUser> findAll();
}
