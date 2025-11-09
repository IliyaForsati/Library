package com.example.Library.service.implementation;

import com.example.Library.model.entity.Asset;
import com.example.Library.service.interfaces.AssetService;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.stream.Collectors;

public sealed class AssetServiceIMP<Entity extends Asset, DTO> implements AssetService<DTO>
        permits BookServiceIMP, MagazineServiceIMP, ReferenceServiceIMP, ThesisServiceIMP {

    protected final JpaRepository<Entity, Long> repository;
    protected final ModelMapper mapper;
    protected final Class<Entity> entityClass;
    protected final Class<DTO> dtoClass;

    @SuppressWarnings("unchecked")
    protected AssetServiceIMP(JpaRepository<Entity, Long> repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;

        var superclass = getClass().getGenericSuperclass();
        if (superclass instanceof ParameterizedType parameterizedType) {
            this.entityClass = (Class<Entity>) parameterizedType.getActualTypeArguments()[0];
            this.dtoClass = (Class<DTO>) parameterizedType.getActualTypeArguments()[1];
        } else {
            throw new IllegalStateException("Cannot determine generic types for " + getClass());
        }
    }

    @Override
    public DTO add(@NonNull DTO dto) {
        Entity created = repository.save(mapper.map(dto, entityClass));
        return mapper.map(created, dtoClass);
    }

    @Override
    public DTO getById(@NonNull Long id) {
        return repository.findById(id)
                .map(entity -> mapper.map(entity, dtoClass))
                .orElseThrow(() -> new IllegalArgumentException("Entity with id " + id + " not found"));
    }

    @Override
    public List<DTO> getAll() {
        return repository.findAll().stream()
                .map(entity -> mapper.map(entity, dtoClass))
                .collect(Collectors.toList());
    }

    @Override
    public DTO update(@NonNull Long targetId, @NonNull DTO src) {
        return repository.findById(targetId)
                .map(existingEntity -> {
                    mapper.map(src, existingEntity);
                    Entity updated = repository.save(existingEntity);
                    return mapper.map(updated, dtoClass);
                })
                .orElseThrow(() -> new IllegalArgumentException("Entity with id " + targetId + " not found"));
    }

    @Override
    public void delete(@NonNull Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Entity with id " + id + " not found");
        }
        repository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }
}
