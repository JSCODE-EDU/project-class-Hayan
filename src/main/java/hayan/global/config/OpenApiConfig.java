package hayan.global.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

@OpenAPIDefinition(
        info = @Info(
                title = "white's board API 명세서",
                description = "API 명세서",
                version = "v1",
                contact = @Contact(
                        name = "white",
                        email = "cwhite7230@gmail.com"
                )
        )
)

@Configuration
public class OpenApiConfig {

}
