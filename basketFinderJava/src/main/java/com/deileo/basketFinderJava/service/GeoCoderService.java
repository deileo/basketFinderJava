package com.deileo.basketFinderJava.service;

import com.google.maps.errors.ApiException;
import com.google.maps.model.LatLng;

import java.io.IOException;

public interface GeoCoderService {
    public LatLng getGeoLocationByAddress(String address) throws InterruptedException, ApiException, IOException;
}
