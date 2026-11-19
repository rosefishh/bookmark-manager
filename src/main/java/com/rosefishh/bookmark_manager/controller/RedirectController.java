package com.rosefishh.bookmark_manager.controller;

import com.rosefishh.bookmark_manager.exception.BookmarkNotFoundException;
import com.rosefishh.bookmark_manager.model.Bookmark;
import com.rosefishh.bookmark_manager.repository.BookmarkRepository;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedirectController {

    private final BookmarkRepository bookmarkRepository;

    public RedirectController(BookmarkRepository bookmarkRepository) {
        this.bookmarkRepository = bookmarkRepository;
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
}
