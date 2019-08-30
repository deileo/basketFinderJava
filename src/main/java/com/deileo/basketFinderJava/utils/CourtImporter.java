package com.deileo.basketFinderJava.utils;

import com.deileo.basketFinderJava.entity.Court;
import com.deileo.basketFinderJava.service.CourtService;
import com.google.maps.errors.ApiException;
import org.springframework.stereotype.Service;
import java.io.FileReader;
import java.io.IOException;

@Service
public class CourtImporter {
    private static final String PUBLIC_COURTS_CSV = "data/basketballCourts.csv";
    private static final String GYM_COURTS_CSV = "data/Sporto_baziu_DB.csv";

    private CourtReader reader;

    private CourtService courtService;

    CourtImporter(CourtReader courtReader, CourtService courtService) {
        this.reader = courtReader;
        this.courtService = courtService;
    }

    public void importPublicCourts() throws IOException, InterruptedException, ApiException {
        FileReader file = new FileReader(ClassLoader.getSystemResource(PUBLIC_COURTS_CSV).getFile());

        for (Court court: reader.readPublicCourts(file)) {
            courtService.save(court);
        }
    }

    public void importGymCourts() throws IOException, InterruptedException, ApiException {
        FileReader file = new FileReader(ClassLoader.getSystemResource(GYM_COURTS_CSV).getFile());

        for (Court court: reader.readGymCourts(file)) {
            courtService.save(court);
        }
    }
}
