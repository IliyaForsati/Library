package com.example.Library.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class BorrowDetailDTO {
    private LocalDate borrowDate;
    private LocalDate returnDate;

    private Long userID;
    private String username;

    private Long assetID;
    private String assetTitle;
}
