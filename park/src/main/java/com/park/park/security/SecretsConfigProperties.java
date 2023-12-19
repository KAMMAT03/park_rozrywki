package com.park.park.security;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("config")
public record SecretsConfigProperties(String jwtSecret) {
}
