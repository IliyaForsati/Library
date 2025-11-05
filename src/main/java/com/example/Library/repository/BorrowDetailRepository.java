package com.example.Library.repository;

import com.example.Library.model.entity.BorrowDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowDetailRepository extends JpaRepository<BorrowDetail, Long> {
}
