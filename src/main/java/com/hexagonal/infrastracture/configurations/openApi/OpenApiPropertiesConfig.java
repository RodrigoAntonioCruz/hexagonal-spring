package com.hexagonal.infrastracture.configurations.openApi;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "spring")
public class OpenApiPropertiesConfig {

    private String appVersion;

}
