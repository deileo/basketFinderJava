package com.deileo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BasketFinderImporterApplication implements CommandLineRunner {

	private CourtImporter importer;

	private static Logger logger = LoggerFactory.getLogger(BasketFinderImporterApplication.class);

	public BasketFinderImporterApplication(CourtImporter importer) {
		this.importer = importer;
	}

	public static void main(String[] args) {
		SpringApplication.run(BasketFinderImporterApplication.class, args).close();
		logger.info("Import finished!");
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("Importing public courts:");
		this.importer.importPublicCourts();

		logger.info("Importing gym courts:");
		this.importer.importPrivateCourts();
	}
}
