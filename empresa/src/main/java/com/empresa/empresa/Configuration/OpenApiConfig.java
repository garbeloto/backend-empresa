package com.empresa.empresa.Configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenApiConfig {

    private static final String SECURITY_SCHEME_NAME = "Bearer Token JWT";

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            // 1. Informações da API
            .info(new Info()
                .title("API de Gestão e Check-in da Empresa")
                .version("v1.0")
                .description("API para gestão de colaboradores, empresas e check-ins de humor."))
            
            // 2. Configuração de Segurança (o esquema que será usado)
            .addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME_NAME))
            .components(new Components()
                .addSecuritySchemes(SECURITY_SCHEME_NAME, new SecurityScheme()
                    .name(SECURITY_SCHEME_NAME)
                    .type(SecurityScheme.Type.HTTP) // Tipo de esquema HTTP
                    .scheme("bearer") // Esquema padrão para JWT
                    .bearerFormat("JWT") // Formato do token
                    .description("Insira o Token JWT no formato: Bearer SEU_TOKEN_AQUI"))); // Instrução para o usuário
    }
}