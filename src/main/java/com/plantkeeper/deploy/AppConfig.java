package com.plantkeeper.deploy;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.plantkeeper.repository")
@EntityScan(basePackages = "com.plantkeeper.entity")
public class AppConfig {

}
