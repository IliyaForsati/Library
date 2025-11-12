package com.example.Library.model.dto;

import com.example.Library.model.enums.UserRole;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private UserRole role = UserRole.USER;
    private List<BorrowDetailDTO> borrowDetailList;
}
