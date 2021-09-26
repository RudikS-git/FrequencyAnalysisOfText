package com.example.webfrequenceanalysisoftext;

import analyzetext.Analyzer;
import com.example.webfrequenceanalysisoftext.service.storage.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ImportResource("classpath:applicationContext.xml")
@EnableConfigurationProperties(StorageProperties.class)
public class WebFrequenceAnalysisOfTextApplication {

    public static void main(String[] args) {

        SpringApplication.run(WebFrequenceAnalysisOfTextApplication.class, args);
    }

}
