package com.example.Library.service.implementation;

import com.example.Library.model.dto.UserDTO;
import com.example.Library.model.entity.User;
import com.example.Library.model.enums.UserRole;
import com.example.Library.repository.UserRepository;
import com.example.Library.service.interfaces.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceIMP implements UserService, UserDetailsService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;
    private final ModelMapper mapper;

    @Autowired
    public UserServiceIMP(UserRepository repository, PasswordEncoder encoder, ModelMapper mapper) {
        this.repository = repository;
        this.encoder = encoder;
        this.mapper = mapper;
    }

    @Override
    public UserDTO add(UserDTO dto) {
        User entity = mapper.map(dto, User.class);
        entity.setPassword(encoder.encode(dto.getPassword()));
        entity = repository.save(entity);
        return mapper.map(entity, UserDTO.class);
    }

    @Override
    public UserDTO getById(Long id) {
        return repository.findById(id)
                .map(user -> mapper.map(user, UserDTO.class))
                .orElseThrow(() -> new IllegalArgumentException("User with id " + id + " not found"));
    }

    @Override
    public UserDTO getByUsername(String username) {
        return repository.findByUsername(username)
                .map(user -> mapper.map(user, UserDTO.class))
                .orElseThrow(() -> new IllegalArgumentException("User with username " + username + " not found"));
    }

    @Override
    public boolean existByUsername(String username) {
        return repository.existsByUsername(username);
    }

    @Override
    public UserDTO getCurrentUser() {
        return repository.findByUsername(
                        SecurityContextHolder.getContext().getAuthentication().getName()
                ).map(user -> mapper.map(user, UserDTO.class))
                .orElseThrow(() -> new IllegalArgumentException("No authenticated user found"));
    }

    @Override
    public List<UserDTO> getAll() {
        return repository.findAll().stream()
                .map(user -> mapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO update(Long targetId, UserDTO src) {
        return repository.findById(targetId)
                .map(user -> {
                    // فقط فیلدهای غیر امنیتی map شود
                    user.setUsername(src.getUsername());
                    user.setRole(src.getRole());
                    User updated = repository.save(user);
                    return mapper.map(updated, UserDTO.class);
                })
                .orElseThrow(() -> new IllegalArgumentException("User with id " + targetId + " not found"));
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id))
            throw new IllegalArgumentException("User with id " + id + " not found");
        repository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public void assignRole(Long id, UserRole role) {
        User user = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User with id " + id + " not found"));
        user.setRole(role);
        repository.save(user);
    }

    @Override
    public void removeRole(Long id) {
        assignRole(id, UserRole.USER);
    }

    @Override
    public boolean changePassword(Long id, String oldPassword, String newPassword) {
        User user = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User with id " + id + " not found"));

        if (!encoder.matches(oldPassword, user.getPassword())) {
            return false;
        }

        user.setPassword(encoder.encode(newPassword));
        repository.save(user);
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with username " + username + " not found"));
    }
}
