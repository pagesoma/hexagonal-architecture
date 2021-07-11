package com.hexagonal.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {

  private String version = "1.4.0.RELEASE";

  private Redis redis = new Redis();

  @Getter
  @Setter
  public static class Redis {

    private String driverLocationKeyPrefix = "driverLocations";
    private Long driverLocationExpiration = 86400L;
  }


}
