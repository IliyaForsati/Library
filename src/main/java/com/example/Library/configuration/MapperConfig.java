package com.example.Library.configuration;

import com.example.Library.model.dto.BorrowDetailDTO;
import com.example.Library.model.entity.BorrowDetail;
import org.hibernate.collection.spi.PersistentBag;
import org.modelmapper.Converter;
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
            m.map(src -> src.getUser().getId(), BorrowDetailDTO::setUserID);
            m.map(src -> src.getUser().getUsername(), BorrowDetailDTO::setUsername);
            m.map(src -> src.getAsset().getId(), BorrowDetailDTO::setAssetID);
            m.map(src -> src.getAsset().getTitle(), BorrowDetailDTO::setAssetTitle);
        });

        mapper.typeMap(BorrowDetailDTO.class, BorrowDetail.class).addMappings(m -> {
            m.skip(BorrowDetail::setUser);
            m.skip(BorrowDetail::setAsset);
        });

        return mapper;
    }
}
