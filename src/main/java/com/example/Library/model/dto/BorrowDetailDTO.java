package com.example.Library.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BorrowDetailDTO {
    private LocalDate borrowDate;
    private LocalDate returnDate;

    private Long userID;
    private String username;

    private Long assetID;
    private String assetTitle;
}
