package online.beautyskin.beauty.config;

import online.beautyskin.beauty.mapper.SkinQuestionMapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(new SkinQuestionMapper());
        return new ModelMapper();
    }
}
