package br.imanage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableJpaRepositories("br.imanage")
@SpringBootApplication
@ComponentScan("br.imanage")
public class ImanageApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImanageApplication.class, args);
    }

}
