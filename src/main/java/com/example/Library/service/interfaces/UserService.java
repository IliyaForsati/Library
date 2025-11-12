package com.example.Library.service.interfaces;

import com.example.Library.model.dto.UserDTO;
import com.example.Library.model.enums.UserRole;
import com.example.Library.service.interfaces.publicInterfaces.CRUD;

public interface UserService extends CRUD<UserDTO> {
    void assignRole(Long id, UserRole role) throws IllegalArgumentException;
    boolean changePassword(Long id, String oldPassword, String newPassword);
    boolean existByUsername(String username);
    UserDTO getCurrentUser() throws IllegalArgumentException;
}
