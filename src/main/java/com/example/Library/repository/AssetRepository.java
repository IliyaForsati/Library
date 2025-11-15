package com.example.Library.repository;

import com.example.Library.model.entity.Asset;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AssetRepository extends JpaRepository<Asset, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT a FROM Asset a WHERE a.id = :id")
    Optional<Asset> findByIdForUpdate(Long id);
}
