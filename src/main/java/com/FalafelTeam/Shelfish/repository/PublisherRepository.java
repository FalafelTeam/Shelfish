package com.FalafelTeam.Shelfish.repository;

import com.FalafelTeam.Shelfish.model.Document;
import com.FalafelTeam.Shelfish.model.Publisher;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * interface for the PublisherRepository
 */
@Transactional
public interface PublisherRepository extends CrudRepository<Publisher, Integer> {

    Publisher findByName(String name);
}
