package com.upiiz.alumnos.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "API de Gestión de Alumnos",
                version = "1.0.0", // Basado en tu imagen de versionado
                description = "API REST para el ejercicio 07 de la clase WC.",
                contact = @Contact(
                        name = "E.A.M.", // De tu nomenclatura de archivo
                        email = "tu-correo@upiiz.com",
                        url = "https://www.upiiz.ipn.mx"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.apache.org/licenses/LICENSE-2.0.html"
                ),
                termsOfService = "https://tus-terminos.com/servicio"
        ),
        servers = {
                @Server(url = "http://localhost:8080", description = "Servidor de Desarrollo Local"),
                @Server(url = "https://wc-ejercicio07-eam.onrender.com", description = "Servidor de Producción (Render)")
        }
)
public class SwaggerConfig {
    // Esta clase solo necesita las anotaciones
}