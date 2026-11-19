package com.rosefishh.bookmark_manager.repository;

import com.rosefishh.bookmark_manager.model.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    List<Bookmark> findByTagsName(String tagName);
    Bookmark findByShortCode(String shortCode);
}