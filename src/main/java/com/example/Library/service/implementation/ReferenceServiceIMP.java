package com.example.Library.service.implementation;

import com.example.Library.model.dto.ReferenceDTO;
import com.example.Library.model.entity.Reference;
import com.example.Library.service.interfaces.ReferenceService;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public final class ReferenceServiceIMP extends AssetServiceIMP<Reference, ReferenceDTO> implements ReferenceService {
    public ReferenceServiceIMP(JpaRepository<Reference, Long> repository, ModelMapper mapper) {
        super(repository, mapper);
    }
}
