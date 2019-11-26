package com.deileo.basketFinderJava.config;

import com.google.maps.GeoApiContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeoCoderConfig {
    @Value("${google.api.key}")
    private String apiKey;

    public GeoApiContext getContext() {
        return new GeoApiContext.Builder().apiKey(apiKey).build();
    }
}
