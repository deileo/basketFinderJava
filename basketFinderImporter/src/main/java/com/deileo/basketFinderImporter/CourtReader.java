package com.deileo;

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
        Court court = new Court(CourtType.PUBLIC);
        court.setLocation(row[1]);
        court.setAddress(row[2]);
        court.setName(row[2]);
        court.setDescription(row[3]);
        court.setLat(geocode.lat);
        court.setLng(geocode.lng);

        return court;
    }

    private Court createPrivateCourt(String[] row, LatLng geocode) {
        Court court = new Court(CourtType.PRIVATE);
        court.setLocation(row[0]);
        court.setName(row[3]);
        court.setAddress(row[4]);
        court.setDescription(row[7]);
        court.setLat(geocode.lat);
        court.setLng(geocode.lng);

        return court;
    }
}
