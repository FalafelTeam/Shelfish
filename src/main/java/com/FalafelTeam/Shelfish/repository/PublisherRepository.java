package com.FalafelTeam.Shelfish.repository;

import com.FalafelTeam.Shelfish.model.Publisher;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * interface for the PublisherRepository
 */
@Repository
public interface PublisherRepository extends CrudRepository<Publisher, Long> {
}
