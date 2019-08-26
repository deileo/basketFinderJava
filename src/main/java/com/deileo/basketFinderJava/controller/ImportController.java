package com.deileo.basketFinderJava.controller;

import com.deileo.basketFinderJava.service.GeoCoderServiceImpl;
import com.deileo.basketFinderJava.utils.CourtImporter;
import com.google.maps.errors.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/import")
public class ImportController {

    private CourtImporter importer;

    private GeoCoderServiceImpl geoCoder;

    @Autowired
    ImportController(CourtImporter importer, GeoCoderServiceImpl geoCoder) {
        this.importer = importer;
        this.geoCoder = geoCoder;
    }

    @RequestMapping("/courts")
    public String importCourts() {

        try {
            importer.importPublicCourts();
        }
        catch (IOException | InterruptedException | ApiException e) {
            return e.getMessage();
        }

        return "Success!";
    }

    @RequestMapping("/gym-courts")
    public String importGymCourts() {

        try {
            importer.importGymCourts();
        }
        catch (IOException | InterruptedException | ApiException e) {
            return e.getMessage();
        }

        return "Success!";
    }
}
