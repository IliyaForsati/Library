package com.example.Library.model.dto;

import lombok.*;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BookDTO extends AssetDTO {
    private int pageCount;
}
