package com.hexagonal.config;

import javax.sql.DataSource;
import liquibase.integration.spring.SpringLiquibase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class LiquibaseConfiguration {

  @Bean
  public SpringLiquibase liquibase(
      DataSource dataSource, LiquibaseProperties liquibaseProperties) {

    SpringLiquibase liquibase = new SpringLiquibase();
    liquibase.setDataSource(dataSource);
    liquibase.setChangeLog("classpath:db/hexagonal/db.changelog-master.xml");
    liquibase.setContexts(liquibaseProperties.getContexts());
    liquibase.setDefaultSchema(liquibaseProperties.getDefaultSchema());
    liquibase.setDropFirst(false);
    liquibase.setChangeLogParameters(liquibaseProperties.getParameters());
    liquibase.setShouldRun(liquibaseProperties.isEnabled());

    if (log.isDebugEnabled()) {
      log.debug("Liquibase for catalog initialized");
    }

    return liquibase;
  }

}
