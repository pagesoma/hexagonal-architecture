package com.hexagonal;

import com.hexagonal.config.ApplicationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({ApplicationProperties.class, LiquibaseProperties.class})
public class HexagonalApplication {

  public static void main(String[] args) {
    SpringApplication.run(HexagonalApplication.class, args);
  }

}
