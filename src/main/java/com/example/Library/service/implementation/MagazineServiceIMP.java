package com.example.Library.service.implementation;

import com.example.Library.model.dto.MagazineDTO;
import com.example.Library.model.entity.Magazine;
import com.example.Library.service.interfaces.MagazineService;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public final class MagazineServiceIMP extends AssetServiceIMP<Magazine, MagazineDTO> implements MagazineService {
    public MagazineServiceIMP(JpaRepository<Magazine, Long> repository, ModelMapper mapper) {
        super(repository, mapper);
    }
}
