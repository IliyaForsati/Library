package com.example.Library.model.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private String username;
    private String hashedPassword;
    private List<BorrowDetailDTO> borrowDetailList;
}
