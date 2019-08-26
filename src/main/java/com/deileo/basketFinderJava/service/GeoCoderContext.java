package com.deileo.basketFinderJava.service;

import com.google.maps.GeoApiContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GeoCoderContext {

    @Value("${google.api.key}")
    private String apiKey;

    public GeoApiContext getContext() {
        return new GeoApiContext.Builder().apiKey(apiKey).build();
    }
}
