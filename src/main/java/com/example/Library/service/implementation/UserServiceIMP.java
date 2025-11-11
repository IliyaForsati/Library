package com.example.Library.service.implementation;

import com.example.Library.model.dto.UserDTO;
import com.example.Library.model.entity.User;
import com.example.Library.model.enums.UserRole;
import com.example.Library.repository.UserRepository;
import com.example.Library.service.interfaces.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceIMP implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private ModelMapper mapper;

    private User currentUser;

    @Override
    public UserDTO add(UserDTO dto) {
        User entity = mapper.map(dto, User.class);
        entity = repository.save(entity);
        return mapper.map(entity, UserDTO.class);
    }

    @Override
    public UserDTO getById(Long id) throws IllegalArgumentException {
        return repository.findById(id)
                .map(user -> mapper.map(user, UserDTO.class))
                .orElseThrow(() -> new IllegalArgumentException("Entity with id " + id + " not found"));
    }

    @Override
    public UserDTO getByUsername(String username) throws IllegalArgumentException {
        return repository.findByUsername(username)
                .map(user -> mapper.map(user, UserDTO.class))
                .orElseThrow(() -> new IllegalArgumentException("Entity with username " + username + " not found"));
    }

    @Override
    public boolean existByUsername(String username) {
        return repository.existsByUsername(username);
    }

    @Override
    public UserDTO getCurrentUser() throws IllegalArgumentException {
        return mapper.map(currentUser, UserDTO.class);
    }

    @Override
    public List<UserDTO> getAll() {
        return repository.findAll().stream()
                .map(user -> mapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO update(Long targetId, UserDTO src) throws IllegalArgumentException {
        return repository.findById(targetId)
                .map(user -> {
                    mapper.map(src, user);
                    User updated = repository.save(user);
                    return mapper.map(updated, UserDTO.class);
                })
                .orElseThrow(() -> new IllegalArgumentException("Entity with id " + targetId + " not found"));
    }

    @Override
    public void delete(Long id) throws IllegalArgumentException {
        if (!repository.existsById(id))
            throw new IllegalArgumentException("Entity with id " + id + " not found");
        repository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public void assignRole(Long id, UserRole role) throws IllegalArgumentException {
        User user = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Entity with id " + id + " not found"));

        user.setRole(role);
        repository.save(user);
    }

    @Override
    public void removeRole(Long id) throws IllegalArgumentException {
        assignRole(id, UserRole.USER);
    }

    @Override
    public boolean changePassword(Long id, String oldPassword, String newPassword) {
        return false;
    }
}
