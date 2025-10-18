package com.nadic.desafiobackend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.tags.Tag;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerOrderConfig {

    @Bean
    public OpenApiCustomizer sortTagsFirst() {
        return openApi -> {
            List<Tag> tags = openApi.getTags();
            if (tags != null) {
                tags.sort((t1, t2) -> {
                    if (t1.getName().equals("Autenticação")) return -1;
                    if (t2.getName().equals("Autenticação")) return 1;
                    return t1.getName().compareTo(t2.getName());
                });
            }
        };
    }
}

