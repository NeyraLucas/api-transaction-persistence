package com.evaluacion.plataformas.persistencia.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI apiTransaccionPersistenciaOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Transaccion Persistencia")
                        .description("Persiste transacciones recibidas desde la API 1, genera una referencia de 6 digitos y regresa el resultado de la operacion.")
                        .version("v1"));
    }
}
