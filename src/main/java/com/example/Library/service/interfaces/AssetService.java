package com.example.Library.service.interfaces;

import java.util.List;

public interface AssetService<DTO> {
    DTO add(DTO dto);
    DTO getById(Long id);
    List<DTO> getAll();
    DTO update(Long targetId, DTO src);
    void delete(Long id);
    void deleteAll();
}
