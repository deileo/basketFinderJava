package com.deileo.basketFinderJava.utils;

import com.deileo.basketFinderJava.entity.Court;
import com.deileo.basketFinderJava.service.GeoCoderService;
import com.google.maps.errors.ApiException;
import com.google.maps.model.LatLng;
import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

@Service
public class CourtReader {

    private GeoCoderService geoCoderService;

    @Autowired
    CourtReader(GeoCoderService geoCoderService) {
        this.geoCoderService = geoCoderService;
    }

    public List<Court> readPublicCourts(Reader reader) throws IOException, ApiException, InterruptedException {
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

    public List<Court> readGymCourts(Reader reader) throws IOException, ApiException, InterruptedException {
        List<Court> courts = new ArrayList<>();
        String[] row;
        CSVReader courtReader = this.getReader(reader);

        while ((row = courtReader.readNext()) != null) {
            if (row.length > 1 && row[1].equals("Vidaus")) {
                LatLng geocode = geoCoderService.getGeoLocationByAddress(row[4]);
                if (geocode != null) {
                    courts.add(this.createGymCourt(row, geocode));
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
        Court court = new Court();
        court.setLocation(row[1]);
        court.setAddress(row[2]);
        court.setName(row[2]);
        court.setDescription(row[3]);
        court.setLat(geocode.lat);
        court.setLng(geocode.lng);

        return court;
    }

    private Court createGymCourt(String[] row, LatLng geocode) {
        Court court = new Court();
        court.setLocation(row[0]);
        court.setName(row[3]);
        court.setAddress(row[4]);
        court.setDescription(row[7]);
        court.setRenovationYear(Integer.parseInt(row[8].substring(0, 4)));
        court.setLat(geocode.lat);
        court.setLng(geocode.lng);

        return court;
    }
}
