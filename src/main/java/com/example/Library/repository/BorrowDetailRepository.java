package com.example.Library.repository;

import com.example.Library.model.entity.BorrowDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowDetailRepository extends JpaRepository<BorrowDetail, Long> {
    List<BorrowDetail> findByUserId(Long userId);
    List<BorrowDetail> findByAssetIdAndUserId(Long assetId, Long userId);
}
