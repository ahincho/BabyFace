package com.nxtep.configurations;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfiguration {
    @Value("${cloudinary.environment}")
    private String cloudinaryEnvironment;
    @Value("${cloudinary.name}")
    private String cloudinaryName;
    @Value("${cloudinary.api.key}")
    private String cloudinaryApiKey;
    @Value("${cloudinary.api.secret}")
    private String cloudinaryApiSecret;
    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
            "cloud_name", this.cloudinaryName,
            "api_key", this.cloudinaryApiKey,
            "api_secret", this.cloudinaryApiSecret,
            "secure", true
        ));
    }
    @Bean String cloudinaryEnvironment() {
        return this.cloudinaryEnvironment;
    }
}
