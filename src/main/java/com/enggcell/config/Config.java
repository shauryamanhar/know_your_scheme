
package com.enggcell.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ DataSourceConfig.class, JpaConfig.class, RepositoryConfig.class })
public class Config {

}