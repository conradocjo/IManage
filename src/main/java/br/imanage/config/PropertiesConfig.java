package br.imanage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class PropertiesConfig {

    @Bean
    public PropertySourcesPlaceholderConfigurer configurer() {
        var configurer = new PropertySourcesPlaceholderConfigurer();
        configurer.setLocation(new FileSystemResource("application-cjo.properties"));
        return configurer;
    }

}
