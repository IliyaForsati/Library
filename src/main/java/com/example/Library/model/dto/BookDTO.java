package com.example.Library.model.dto;

import lombok.AccessLevel;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class BookDTO extends AssetDTO {
    private int pageCount;
}
