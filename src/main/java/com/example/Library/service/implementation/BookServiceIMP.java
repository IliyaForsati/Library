package com.example.Library.service.implementation;

import com.example.Library.model.dto.BookDTO;
import com.example.Library.model.entity.Book;
import com.example.Library.repository.BookRepository;
import com.example.Library.service.interfaces.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public final class BookServiceIMP extends AssetServiceIMP<Book, BookDTO> implements BookService {
    public BookServiceIMP(BookRepository repository, ModelMapper mapper) {
        super(repository, mapper);
    }
}
