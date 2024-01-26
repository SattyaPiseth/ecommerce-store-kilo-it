package com.example.demo.config.property;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * @author Sombath
 * create at 25/1/24 2:41 AM
 */

@Configuration
@ConfigurationProperties(prefix = "spring.security")
@Data
public class RsaKeyProperties {
    private RSAPublicKey publicKey;
    private RSAPrivateKey privateKey;
}
