	package com.woven.assignment;

	import com.woven.assignment.storage.StorageProperties;
	import com.woven.assignment.storage.StorageService;
	import org.springframework.boot.CommandLineRunner;
	import org.springframework.boot.SpringApplication;
	import org.springframework.boot.autoconfigure.SpringBootApplication;
	import org.springframework.boot.context.properties.EnableConfigurationProperties;
	import org.springframework.context.annotation.Bean;

    @SpringBootApplication
    @EnableConfigurationProperties(StorageProperties.class)
    public class AssignmentApplication {

        public static void main(String[] args) {
            SpringApplication.run(AssignmentApplication.class, args);
        }
        @Bean
        CommandLineRunner init(StorageService storageService) {
            return (args) -> {
                storageService.init();

            };
        }
    }
