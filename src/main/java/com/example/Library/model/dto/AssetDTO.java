package com.example.Library.model.dto;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
public abstract class AssetDTO {
    private String title;
    private String author;
    private int releaseDate;
    private List<BorrowDetailDTO> borrowDetailList;
}
