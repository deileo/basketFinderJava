package com.deileo.basketFinderImporter.utils;

import com.deileo.basketFinderJava.entity.Court;
import com.deileo.basketFinderJava.entity.CourtType;
import com.deileo.basketFinderJava.service.GeoCoderService;
import com.google.maps.model.LatLng;
import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

@Service
class CourtReader {

    private static final String TYPE_PRIVATE_COURT = "Vidaus";

    private final GeoCoderService geoCoderService;

    @Autowired
    CourtReader(GeoCoderService geoCoderService) {
        this.geoCoderService = geoCoderService;
    }

    List<Court> readPublicCourts(Reader reader) throws IOException {
        List<Court> courts = new ArrayList<>();
        String[] row;
        CSVReader courtReader = this.getReader(reader);

        while ((row = courtReader.readNext()) != null) {
            LatLng geocode = geoCoderService.getGeoLocationByAddress(row[2]);
            if (geocode != null) {
                courts.add(this.createPublicCourt(row, geocode));
            }
        }

        return courts;
    }

    List<Court> readPrivateCourts(Reader reader) throws IOException {
        List<Court> courts = new ArrayList<>();
        String[] row;
        CSVReader courtReader = this.getReader(reader);

        while ((row = courtReader.readNext()) != null) {
            if (row.length > 1 && row[1].equals(TYPE_PRIVATE_COURT)) {
                LatLng geocode = geoCoderService.getGeoLocationByAddress(row[4]);
                if (geocode != null) {
                    courts.add(this.createPrivateCourt(row, geocode));
                }
            }
        }

        return courts;
    }

    private CSVReader getReader(Reader reader) throws IOException {
        CSVReader csvReader = new CSVReader(reader);
        csvReader.readNext(); // skip header

        return csvReader;
    }

    private Court createPublicCourt(String[] row, LatLng geocode) {
        return new Court.Builder(CourtType.PUBLIC)
                .setLocation(row[1])
                .setName(row[2])
                .setAddress(row[2])
                .setDescription(row[3])
                .setLat(geocode.lat)
                .setLng(geocode.lng)
                .enable()
                .build();
    }

    private Court createPrivateCourt(String[] row, LatLng geocode) {
        return new Court.Builder(CourtType.PRIVATE)
                .setLocation(row[0])
                .setName(row[3])
                .setAddress(row[4])
                .setDescription(row[7])
                .setLat(geocode.lat)
                .setLng(geocode.lng)
                .enable()
                .build();
    }
}
