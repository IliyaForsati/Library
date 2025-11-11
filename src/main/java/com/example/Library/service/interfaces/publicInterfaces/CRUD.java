package com.example.Library.service.interfaces.publicInterfaces;

import java.util.List;

public interface CRUD<T> {
    T add(T dto);
    T getById(Long id) throws IllegalArgumentException;
    List<T> getAll();
    T update(Long targetId, T src) throws IllegalArgumentException;
    void delete(Long id) throws IllegalArgumentException;
    void deleteAll();
}
