package com.example.Library.service;

import com.example.Library.model.dto.BookDTO;
import com.example.Library.service.interfaces.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AssetServiceTests {

    @Autowired
    private BookService bookService;

    private BookDTO sampleBook;

    @BeforeEach
    void setUp() {
        sampleBook = BookDTO.builder()
                .title("Clean Code")
                .author("Robert C. Martin")
                .releaseDate(2009)
                .pageCount(464)
                .build();

        bookService.deleteAll();
    }

    @Test
    void addTest() {
        // add
        BookDTO saved = bookService.add(sampleBook);

        // assert
        assertThat(saved.getTitle()).isEqualTo(sampleBook.getTitle());
        assertThat(saved.getAuthor()).isEqualTo(sampleBook.getAuthor());
        assertThat(saved.getPageCount()).isEqualTo(sampleBook.getPageCount());
    }

    @Test
    void getByIdTest() {
        // add
        BookDTO saved = bookService.add(sampleBook);

        // get by id
        BookDTO gotten = bookService.getById(saved.getId());

        assertThat(saved.getTitle()).isEqualTo(gotten.getTitle());
        assertThat(saved.getAuthor()).isEqualTo(gotten.getAuthor());
        assertThat(saved.getPageCount()).isEqualTo(gotten.getPageCount());
    }

    @Test
    void updateTest() {
        BookDTO saved = bookService.add(sampleBook);

        saved.setPageCount(500);
        saved = bookService.update(saved.getId(), saved);

        assertEquals(500, saved.getPageCount());
    }

    @Test
    void deleteTest() {
        BookDTO saved = bookService.add(sampleBook);

        Long id = saved.getId();
        bookService.delete(id);

        assertThrows(IllegalArgumentException.class, () -> bookService.getById(id));
    }
}
