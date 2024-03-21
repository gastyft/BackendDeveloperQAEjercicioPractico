 
package com.backendDeveloperQA.BackendDeveloperQA;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

 @EnableWebMvc
@Configuration
    
public class WebConfig   implements WebMvcConfigurer {  
        @Override
    public void addCorsMappings(CorsRegistry registry){
registry.addMapping("/**");
            }
    }