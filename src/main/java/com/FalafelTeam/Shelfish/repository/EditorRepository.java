package com.FalafelTeam.Shelfish.repository;

import com.FalafelTeam.Shelfish.model.Editor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * interface for the EditorRepository
 */
@Repository
public interface EditorRepository extends CrudRepository<Editor, Long> {
}
