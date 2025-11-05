package com.example.Library.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserDTO {
    private String username;
    private String hashedPassword;
    private List<BorrowDetailDTO> borrowDetailList;
}
