package com.example.Library.repository;

import com.example.Library.model.entity.Thesis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThesisController extends JpaRepository<Thesis, Long> {
}
