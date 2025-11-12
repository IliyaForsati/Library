package com.example.Library.model.dto;

import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class BorrowDetailDTO {
    private String username;
    private String assetTitle;
    private LocalDate borrowDate;
    private LocalDate returnDate;
}
