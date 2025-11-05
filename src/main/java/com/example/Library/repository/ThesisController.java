package com.example.Library.repository;

import com.example.Library.model.entity.Thesis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThesisController extends JpaRepository<Thesis, Long> {
}
