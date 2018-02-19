package com.FalafelTeam.Shelfish.repository;

import com.FalafelTeam.Shelfish.model.Editor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * interface for the EditorRepository
 */
@Transactional
public interface EditorRepository extends CrudRepository<Editor,Integer> {
}
