package com.example.Library.service.implementation;

import com.example.Library.model.dto.ThesisDTO;
import com.example.Library.model.entity.Thesis;
import com.example.Library.service.interfaces.ThesisService;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public final class ThesisServiceIMP extends AssetServiceIMP<Thesis, ThesisDTO> implements ThesisService {
    public ThesisServiceIMP(JpaRepository<Thesis, Long> repository, ModelMapper mapper) {
        super(repository, mapper);
    }
}
