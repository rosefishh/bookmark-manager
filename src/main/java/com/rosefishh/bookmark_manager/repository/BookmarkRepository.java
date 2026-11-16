package com.rosefishh.bookmark_manager.repository;

import com.rosefishh.bookmark_manager.model.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
}