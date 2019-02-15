package com.enggcell.config;


import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class DataSourceConfig {

    @Autowired
    private Environment env;
  
     @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        //dataSource.setUrl("jdbc:postgresql://138.197.13.255:5432/Codeutsava");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/Codeutsava");
        dataSource.setUsername("postgres");
        dataSource.setPassword("root");
        //dataSource.setPassword("Abc123");
        dataSource.setMaxActive(100);
        dataSource.setMaxIdle(30);
        dataSource.setMaxWait(2000);
        return dataSource;
    }
     
}
