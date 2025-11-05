package com.example.Library.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class BorrowDetailDTO {
    private UserDTO user;
    private AssetDTO asset;

    private LocalDate borrowDate;
    private LocalDate returnDate;
}
