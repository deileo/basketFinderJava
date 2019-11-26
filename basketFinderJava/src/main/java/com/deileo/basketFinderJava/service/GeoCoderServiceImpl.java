package com.deileo.basketFinderJava.service;

import com.deileo.basketFinderJava.config.GeoCoderConfig;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeoCoderServiceImpl implements GeoCoderService {

    private final GeoCoderConfig geoCoder;

    @Autowired
    public GeoCoderServiceImpl(GeoCoderConfig geoCoder) {
        this.geoCoder = geoCoder;
    }

    public LatLng getGeoLocationByAddress(String address) {
        GeocodingResult[] locations = new GeocodingResult[0];
        try {
            locations = GeocodingApi.geocode(geoCoder.getContext(), address).await();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return locations.length > 0 ? locations[0].geometry.location : null;
    }
}
