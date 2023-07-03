package com.dh.projetoIntegrador.configuracao;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@TestConfiguration
public class BatatinhaConfiguration {



        @Bean
        @Primary
        public DataSource dataSource() {
            DriverManagerDataSource dataSource = new DriverManagerDataSource();
            // Set the properties for the first database
            dataSource.setUrl("jdbc:h2:~/consultorio-teste");
            dataSource.setUsername("sa");
            dataSource.setPassword("");
            return dataSource;
        }

}
