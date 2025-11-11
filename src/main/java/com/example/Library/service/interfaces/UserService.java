package com.example.Library.service.interfaces;

import com.example.Library.model.dto.UserDTO;
import com.example.Library.model.enums.UserRole;
import com.example.Library.service.interfaces.publicInterfaces.CRUD;

public interface UserService extends CRUD<UserDTO> {
    void assignRole(Long id, UserRole role) throws IllegalArgumentException;
    void removeRole(Long id) throws IllegalArgumentException;
    boolean changePassword(Long id, String oldPassword, String newPassword);
    UserDTO getByUsername(String username) throws IllegalArgumentException;
    boolean existByUsername(String username);
    UserDTO getCurrentUser() throws IllegalArgumentException;
}
