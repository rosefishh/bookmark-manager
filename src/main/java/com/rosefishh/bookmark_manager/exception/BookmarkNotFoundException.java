package com.rosefishh.bookmark_manager.exception;

public class BookmarkNotFoundException extends RuntimeException {

    public BookmarkNotFoundException(String message) {
        super(message);
    }
}
