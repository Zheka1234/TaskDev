package com.miskevich.configuration;

import com.miskevich.mappers.UserMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapStructConfig {

    @Bean
    public UserMapper userMapper() {

        return Mappers.getMapper(UserMapper.class);
    }

}
