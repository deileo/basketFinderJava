package com.deileo;

import com.deileo.basketFinderJava.service.CourtService;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;

@Service
class CourtImporter {

    private static final String PUBLIC_COURTS_CSV = "data/basketballCourts.csv";

    private static final String PRIVATE_COURTS_CSV = "data/Sporto_baziu_DB.csv";

    private final CourtReader reader;

    private final CourtService courtService;

    CourtImporter(CourtReader courtReader, CourtService courtService) {
        reader = courtReader;
        this.courtService = courtService;
    }

    void importPublicCourts() throws IOException {
        FileReader file = new FileReader(ClassLoader.getSystemResource(PUBLIC_COURTS_CSV).getFile());

        reader.readPublicCourts(file).forEach(court -> {
            System.out.println(court.getName() + " " + court.getAddress());
            courtService.save(court);
        });
    }

    void importPrivateCourts() throws IOException {
        FileReader file = new FileReader(ClassLoader.getSystemResource(PRIVATE_COURTS_CSV).getFile());

        reader.readPrivateCourts(file).forEach(court -> {
            System.out.println(court.getName() + " " + court.getAddress());
            courtService.save(court);
        });
    }
}
