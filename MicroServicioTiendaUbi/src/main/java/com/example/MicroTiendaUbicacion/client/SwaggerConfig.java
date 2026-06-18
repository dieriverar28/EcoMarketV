package com.example.MicroTiendaUbicacion.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;



@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
            .info(new Info()
                    .title("API 2026 Servicio de Ubicación de Tiendas")
                    .version("1.0")
                    .description("Documentacion de la API para registro y consulta de ubicacion de tiendas"));
                            
    }
}
