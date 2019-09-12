package com.deileo.basketFinderJava.controller;

import com.deileo.basketFinderJava.entity.Court;
import com.deileo.basketFinderJava.entity.CourtType;
import com.deileo.basketFinderJava.payload.CourtDto;
import com.deileo.basketFinderJava.service.CourtService;
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

    @GetMapping("/public")
    @ResponseBody
    public List<CourtDto> getPublicCourts() {
        return courtService.getCourtsByType(CourtType.PUBLIC);
    }

    @GetMapping("/private")
    @ResponseBody
    public List<CourtDto> getPrivateCourts() {
        return courtService.getCourtsByType(CourtType.PRIVATE);
    }

    @GetMapping("/{court}")
    public Court getCourt(Court court) {
        return court;
    }

    @PostMapping()
    public ResponseEntity<String> addCourt(@Valid @RequestBody Court court) {
        courtService.save(court);

        return new ResponseEntity<>("Success!", HttpStatus.CREATED);
    }

    @GetMapping("/delete/{court}")
    public String deleteCourt(Court court) {
        courtService.delete(court);

        return "Success!";
    }
}
