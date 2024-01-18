package com.robot.security.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Jwt parameters configuration
 */

@Data
@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JwtSecurityProperties {

    /** Request Headers ： Authorization */
    private String header;

    /** prefix of token: Bearer */
    private String tokenStartWith;

    /** 88 bits base 64 */
    private String base64Secret;

    /** time out (ms) */
    private Long tokenValidityInSeconds;

    /** to count how many users in the app */
    private String onlineKey;

    /** code key */
    private String codeKey;

    /**token prefix */
    public String getTokenStartWith() {
        return tokenStartWith + " ";
    }
}
