package uz.unicorn.rentme;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.context.request.WebRequest;
import uz.unicorn.rentme.entity.base.SecurityAuditorAware;
import uz.unicorn.rentme.property.OpenApiProperties;
import uz.unicorn.rentme.property.ServerProperties;

import java.util.LinkedHashMap;
import java.util.Map;

@SpringBootApplication
@OpenAPIDefinition
@EnableConfigurationProperties(
        {ServerProperties.class,
                OpenApiProperties.class}
)
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class RentMeApplication {


    public static void main(String[] args) {
        SpringApplication.run(RentMeApplication.class, args);
    }

    @Bean
    AuditorAware<Long> auditorAware() {
        return new SecurityAuditorAware();
    }

    @Bean
    public ErrorAttributes errorAttributes() {
        return new DefaultErrorAttributes() {
            @Override
            public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
                Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, options);
                Object timestamp = System.currentTimeMillis();
                Object status = errorAttributes.get("status");
                Object message = errorAttributes.get("message");
                Map<String, Object> responseEntity = new LinkedHashMap<>();
                responseEntity.put("data", null);
                responseEntity.put("errorMassage", message);
                responseEntity.put("timestamp", timestamp);
                responseEntity.put("status", status);
                return responseEntity;
            }
        };
    }

}
