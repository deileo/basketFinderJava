package com.deileo.basketFinderJava.service;

import com.google.maps.model.LatLng;

public interface GeoCoderService {

    LatLng getGeoLocationByAddress(String address);
}
