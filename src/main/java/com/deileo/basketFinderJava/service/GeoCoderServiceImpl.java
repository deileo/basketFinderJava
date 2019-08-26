package com.deileo.basketFinderJava.service;

import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class GeoCoderServiceImpl implements GeoCoderService {

    private GeoCoderContext context;

    @Autowired
    GeoCoderServiceImpl(GeoCoderContext context) {
        this.context = context;
    }

    public LatLng getGeoLocationByAddress(String address) throws InterruptedException, ApiException, IOException {

        GeocodingResult[] locations = GeocodingApi.geocode(context.getContext(), address).await();

        return locations.length > 0 ? locations[0].geometry.location : null;
    }
}
