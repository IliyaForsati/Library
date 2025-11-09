package com.example.Library.repository;

import com.example.Library.model.entity.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AssetRepositoryTests {
    @Autowired
    private BookRepository bookRepository;

    private Book book;
    @BeforeEach
    void setBookAndDeleteAll() {
        book = new Book();
        book.setTitle("a good title");
        book.setAuthor("Ali");
        book.setReleaseDate(1991);
        book.setPageCount(540);

        bookRepository.deleteAll();
    }

    @Test
    void addTest() {
        var added = bookRepository.save(book);

        System.out.println(added);

        assertEquals(added, book);
    }

}
