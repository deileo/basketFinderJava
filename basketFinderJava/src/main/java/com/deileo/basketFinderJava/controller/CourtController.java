package com.deileo.basketFinderJava.controller;

import com.deileo.basketFinderJava.entity.Court;
import com.deileo.basketFinderJava.entity.CourtType;
import com.deileo.basketFinderJava.payload.CourtDto;
import com.deileo.basketFinderJava.service.CourtService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/courts", produces = MediaType.APPLICATION_JSON_VALUE)
public class CourtController {

    private final CourtService courtService;

    private final ModelMapper modelMapper;

    @Autowired
    public CourtController(CourtService courtService, ModelMapper modelMapper) {
        this.courtService = courtService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/public")
    public ResponseEntity<List<CourtDto>> getPublicCourts() {
        return ResponseEntity.ok(courtService.getCourtsByType(CourtType.PUBLIC));
    }

    @GetMapping("/private")
    public ResponseEntity<List<CourtDto>> getPrivateCourts() {
        return ResponseEntity.ok(courtService.getCourtsByType(CourtType.PRIVATE));
    }

    @GetMapping("/{court}")
    public ResponseEntity<CourtDto> getCourt(Court court) {
        return ResponseEntity.ok(modelMapper.map(court, CourtDto.class));
    }

    @PostMapping()
    public ResponseEntity<String> addCourt(@Valid @RequestBody Court court) {
        courtService.save(court);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{court}")
    public ResponseEntity<String> deleteCourt(Court court) {
        courtService.delete(court);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
