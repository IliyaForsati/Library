package com.example.Library.controller;

// <editor-fold desc="Imports">
import com.example.Library.model.dto.BookDTO;
import com.example.Library.service.interfaces.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;
// </editor-fold>

@RestController
@RequestMapping("/api/books")
public class BookController {

    // <editor-fold desc="Injected Dependencies">
    @Autowired private BookService bookService;
    @Autowired private ModelMapper mapper;
    // </editor-fold>

    // <editor-fold desc="End Points">
    @PostMapping
    public ResponseEntity<BookDTO> add(@RequestBody DTO.BookRecord bookRecord) {
        BookDTO bookDTO = mapper.map(bookRecord, BookDTO.class);
        BookDTO created = bookService.add(bookDTO);
        URI location = URI.create("/api/books/" + created.getId());
        return ResponseEntity.created(location).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getById(@PathVariable Long id) {
        try {
            BookDTO book = bookService.getById(id);
            return ResponseEntity.ok(book);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAll() {
        List<BookDTO> books = bookService.getAll();
        return ResponseEntity.ok(books);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> update(@PathVariable Long id, @RequestBody DTO.BookRecord bookRecord) {
        try {
            BookDTO bookDTO = mapper.map(bookRecord, BookDTO.class);
            BookDTO updated = bookService.update(id, bookDTO);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            bookService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAll() {
        bookService.deleteAll();
        return ResponseEntity.noContent().build();
    }
    // </editor-fold>
}
