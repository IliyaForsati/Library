package com.example.Library.configuration;

import com.example.Library.model.dto.*;
import com.example.Library.model.entity.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper mapper() {
        ModelMapper mapper = new ModelMapper();

        mapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.STRICT);

        // BorrowDetail
        mapper.typeMap(BorrowDetail.class, BorrowDetailDTO.class).addMappings(m -> {
            m.map(src -> src.getUser().getUsername(), BorrowDetailDTO::setUsername);
            m.map(src -> src.getAsset().getTitle(), BorrowDetailDTO::setAssetTitle);
        });

        mapper.typeMap(BorrowDetailDTO.class, BorrowDetail.class).addMappings(m -> {
            m.skip(BorrowDetail::setUser);
            m.skip(BorrowDetail::setAsset);
        });

        // Book
        mapper.typeMap(BookDTO.class, Book.class).addMappings(m -> {
            m.skip(Book::setBorrowDetailList);
        });

        // Magazine
        mapper.typeMap(MagazineDTO.class, Magazine.class).addMappings(m -> {
            m.skip(Magazine::setBorrowDetailList);
        });

        // Reference
        mapper.typeMap(ReferenceDTO.class, Reference.class).addMappings(m -> {
            m.skip(Reference::setBorrowDetailList);
        });

        // Thesis
        mapper.typeMap(ThesisDTO.class, Thesis.class).addMappings(m -> {
            m.skip(Thesis::setBorrowDetailList);
        });


        return mapper;
    }
}
