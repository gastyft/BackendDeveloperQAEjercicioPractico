package com.proyectoDevQA.dev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
<<<<<<< HEAD
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
 
=======
 import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
>>>>>>> d4b846b908b7575b998ff69d958fb9cabf151bb5

@SpringBootApplication

public class DevApplication {

	public static void main(String[] args) {
		SpringApplication.run(DevApplication.class, args);
	}
<<<<<<< HEAD

=======
>>>>>>> d4b846b908b7575b998ff69d958fb9cabf151bb5
 @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/empleados/**")
                        .allowedOrigins("https://desafiodevqa.web.app")
                        .allowedMethods("*")
                        .allowedHeaders("*");
            }
        };
    }
}
