package com.sbtech.erp.common.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi api(){
        return GroupedOpenApi.builder()
                .group("public")
                .pathsToMatch("/**")
                .addOpenApiCustomizer(openApi -> {
                    openApi.getComponents().addParameters("Authorization", createHeader("Authorization"));
                })
                .build();
    }
    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI()
                .info(new Info().title("SBTECH-ERP SYSTEM API DOCUMENT")
                        .version("1.0.0"))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")))
                .servers(List.of(
                        new io.swagger.v3.oas.models.servers.Server()
                                .url("https://sbtech-erp.ddns.net")   // ✅ 여기서 URL 지정
                                .description("Production Server"),
                        new io.swagger.v3.oas.models.servers.Server()
                                .url("http://localhost:30300")       // ✅ 예: 로컬 개발 서버도 추가 가능
                                .description("Local Development Server")
                ));
    }
    private Parameter createHeader(String name){
        return new Parameter()
                .in("header")
                .schema(new io.swagger.v3.oas.models.media.StringSchema())
                .name(name)
                .description(name)
                .required(false);
    }
}
