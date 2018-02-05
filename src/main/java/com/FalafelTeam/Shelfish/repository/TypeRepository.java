package com.FalafelTeam.Shelfish.repository;

import com.FalafelTeam.Shelfish.model.Type;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * interface for the TypeRepository
 */
@Repository
public interface TypeRepository extends CrudRepository<Type, Long> {
}
