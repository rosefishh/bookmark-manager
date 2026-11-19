package com.rosefishh.bookmark_manager.controller;

import com.rosefishh.bookmark_manager.dto.BookmarkRequest;
import com.rosefishh.bookmark_manager.dto.BookmarkResponse;
import com.rosefishh.bookmark_manager.exception.BookmarkNotFoundException;
import com.rosefishh.bookmark_manager.model.Bookmark;
import com.rosefishh.bookmark_manager.repository.BookmarkRepository;
import com.rosefishh.bookmark_manager.service.BookmarkService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
@RequestMapping("/api/bookmarks")
public class BookmarkController {

    private final BookmarkService bookmarkService;
    private final BookmarkRepository bookmarkRepository;

    public BookmarkController(BookmarkService bookmarkService, BookmarkRepository bookmarkRepository) {
        this.bookmarkService = bookmarkService;
        this.bookmarkRepository = bookmarkRepository;
    }

    @GetMapping
    public ResponseEntity<List<BookmarkResponse>> getAllBookmarks() {
        List<BookmarkResponse> bookmarks = bookmarkService.getAllBookmarks();
        return ResponseEntity.ok(bookmarks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookmarkResponse> getBookmarkById(@PathVariable Long id) {
        BookmarkResponse bookmark = bookmarkService.getBookmarkById(id);
        return ResponseEntity.ok(bookmark);
    }

    @GetMapping("/r/{shortCode}")
    public RedirectView redirectToUrl(@PathVariable String shortCode) {
        Bookmark bookmark = bookmarkRepository.findByShortCode(shortCode);
        if (bookmark == null) {
            throw new BookmarkNotFoundException("Short code not found: " + shortCode);
        }
        RedirectView redirectView = new RedirectView(bookmark.getUrl());
        return redirectView;
    }

    @GetMapping("/tag/{tagName}")
    public ResponseEntity<List<BookmarkResponse>> getBookmarksByTag(@PathVariable String tagName) {
        List<BookmarkResponse> bookmarks = bookmarkService.findBookmarksByTag(tagName);
        return ResponseEntity.ok(bookmarks);
    }

    @PostMapping
    public ResponseEntity<BookmarkResponse> createBookmark(@Valid @RequestBody BookmarkRequest request) {
        BookmarkResponse created = bookmarkService.createBookmark(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookmarkResponse> updateBookmark(
            @PathVariable Long id,
            @Valid @RequestBody BookmarkRequest request) {
        BookmarkResponse updated = bookmarkService.updateBookmark(id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookmark(@PathVariable Long id) {
        bookmarkService.deleteBookmark(id);
        return ResponseEntity.noContent().build();
    }
}
