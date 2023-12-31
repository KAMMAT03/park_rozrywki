package com.park.park;

import com.park.park.security.SecretsConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(SecretsConfigProperties.class)
public class ParkApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParkApplication.class, args);
	}

}
