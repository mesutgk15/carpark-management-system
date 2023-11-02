package project.carparkmanagement.carparkservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition
public class OpenApiConfig {

    @Bean
    public OpenAPI baseOpenAPI(){
        Components components = new Components();
        return new OpenAPI().components(components)
                .info(new Info()
                        .title("Carpark-Management-System OpenAPI Docs")
                        .version("0.0.1-SNAPSHOT").description("Documentation contains the featues of carpark-service"));
    }

}
