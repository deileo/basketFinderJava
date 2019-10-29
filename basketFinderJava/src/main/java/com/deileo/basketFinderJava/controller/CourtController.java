package com.deileo.basketFinderJava.controller;

import com.deileo.basketFinderJava.entity.Court;
import com.deileo.basketFinderJava.entity.CourtType;
import com.deileo.basketFinderJava.payload.CourtDto;
import com.deileo.basketFinderJava.service.CourtService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/courts")
public class CourtController {

    @Autowired
    private CourtService courtService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/public")
    @ResponseBody
    public ResponseEntity<List<CourtDto>> getPublicCourts() {
        return ResponseEntity.ok(courtService.getCourtsByType(CourtType.PUBLIC));
    }

    @GetMapping("/private")
    @ResponseBody
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

        return new ResponseEntity<>("Success!", HttpStatus.CREATED);
    }

    @GetMapping("/delete/{court}")
    public ResponseEntity<String> deleteCourt(Court court) {
        courtService.delete(court);

        return ResponseEntity.ok("Success!");
    }
}
