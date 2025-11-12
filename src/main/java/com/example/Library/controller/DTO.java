package com.example.Library.controller;

public interface DTO {
    record UserRecord(String username, String password) {}
    record BookRecord(String title, String author, int releaseDate, int pageCount) {}
    record MagazineRecord(String title, String author, int releaseDate) {}
    record ReferenceRecord(String title, String author, int releaseDate) {}
    record ThesisRecord(String title, String author, int releaseDate) {}
}