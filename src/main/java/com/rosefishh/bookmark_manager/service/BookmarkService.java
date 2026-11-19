package com.rosefishh.bookmark_manager.service;

import com.rosefishh.bookmark_manager.dto.BookmarkRequest;
import com.rosefishh.bookmark_manager.dto.BookmarkResponse;
import com.rosefishh.bookmark_manager.exception.BookmarkNotFoundException;
import com.rosefishh.bookmark_manager.model.Bookmark;
import com.rosefishh.bookmark_manager.model.Tag;
import com.rosefishh.bookmark_manager.repository.BookmarkRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;

    public BookmarkService(BookmarkRepository bookmarkRepository) {
        this.bookmarkRepository = bookmarkRepository;
    }

    public List<BookmarkResponse> getAllBookmarks() {
        return bookmarkRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public BookmarkResponse getBookmarkById(Long id) {
        Bookmark bookmark = bookmarkRepository.findById(id)
                .orElseThrow(() -> new BookmarkNotFoundException("Bookmark not found with id: " + id));
        return toResponse(bookmark);
    }

    public BookmarkResponse createBookmark(BookmarkRequest request) {
        Bookmark bookmark = new Bookmark(request.getTitle(), request.getUrl(), request.getDescription());
        Bookmark saved = bookmarkRepository.save(bookmark);
        return toResponse(saved);
    }

    public BookmarkResponse updateBookmark(Long id, BookmarkRequest request) {
        Bookmark bookmark = bookmarkRepository.findById(id)
                .orElseThrow(() -> new BookmarkNotFoundException("Bookmark not found with id: " + id));
        bookmark.setTitle(request.getTitle());
        bookmark.setUrl(request.getUrl());
        bookmark.setDescription(request.getDescription());
        Bookmark saved = bookmarkRepository.save(bookmark);
        return toResponse(saved);
    }

    public void deleteBookmark(Long id) {
        if (!bookmarkRepository.existsById(id)) {
            throw new BookmarkNotFoundException("Bookmark not found with id: " + id);
        }
        bookmarkRepository.deleteById(id);
    }

    private BookmarkResponse toResponse(Bookmark bookmark) {
        List<String> tagNames = bookmark.getTags().stream()
                .map(Tag::getName)
                .collect(Collectors.toList());
        return new BookmarkResponse(
                bookmark.getId(),
                bookmark.getTitle(),
                bookmark.getUrl(),
                bookmark.getDescription(),
                bookmark.getCreatedAt(),
                tagNames
        );
    }
}