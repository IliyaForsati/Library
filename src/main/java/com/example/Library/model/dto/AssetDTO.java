package com.example.Library.model.dto;

import lombok.AccessLevel;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@SuperBuilder
public abstract class AssetDTO {
    private Long Id;
    private String title;
    private String author;
    private int releaseDate;
    private List<BorrowDetailDTO> borrowDetailList = new ArrayList<>();
}
