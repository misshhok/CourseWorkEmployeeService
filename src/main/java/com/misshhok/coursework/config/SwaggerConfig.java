package com.misshhok.coursework.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import java.util.List;

public class SwaggerConfig {
  @Bean
  public GroupedOpenApi publicUserApi() {
    return GroupedOpenApi.builder()
      .group("Employers")
      .pathsToMatch("/employers/**")
      .build();
  }

  @Bean
  public OpenAPI customOpenApi(@Value("${application-description}")String appDescription,
                               @Value("${application-version}")String appVersion) {
    return new OpenAPI()
      .info(new Info().title("Спецификация API для сервиса управления кадровым агентством")
        .version(appVersion)
        .description(appDescription)
        .license(new License().name("Apache 2.0")
          .url("http://springdoc.org"))
        .contact(new Contact().name("misshhok")
          .email("misshhok@gmail.com")))
        .servers(List.of(new Server()
          .url("http://localhost:8080")
          .description("Dev service")));
  }
}
