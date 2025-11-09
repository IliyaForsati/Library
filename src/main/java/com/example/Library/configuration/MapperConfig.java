package com.example.Library.configuration;

import com.example.Library.model.dto.BorrowDetailDTO;
import com.example.Library.model.entity.BorrowDetail;
import org.hibernate.collection.spi.PersistentBag;
import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;


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

        // TODO: write a better converter
        mapper.addConverter(new AbstractConverter<PersistentBag, List>() {
            @Override
            protected List convert(PersistentBag source) {
                return source;
            }
        });

        return mapper;
    }
}
