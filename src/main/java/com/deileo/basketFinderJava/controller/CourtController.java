package com.deileo.basketFinderJava.controller;

import com.deileo.basketFinderJava.entity.Court;
import com.deileo.basketFinderJava.service.CourtService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Court> getPublicCourts() {
        return courtService.getCourtsByType(Court.TYPE_PUBLIC);
    }

    @GetMapping("/private")
    @ResponseBody
    public List<Court> getPrivateCourts() {
        return courtService.getCourtsByType(Court.TYPE_PRIVATE);
    }

    @GetMapping("/{court}")
    public Court getCourt(Court court) {
        return court;
    }

    @PostMapping()
    public ResponseEntity<String> addCourt(@Valid @RequestBody Court court) {
        courtService.save(court);

        return ResponseEntity.ok("Success");
    }

    @GetMapping("/delete/{court}")
    public String deleteCourt(Court court) {
        courtService.delete(court);

        return "Success!";
    }
}
