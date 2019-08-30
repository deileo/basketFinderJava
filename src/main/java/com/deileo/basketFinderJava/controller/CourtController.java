package com.deileo.basketFinderJava.controller;

import com.deileo.basketFinderJava.entity.Court;
import com.deileo.basketFinderJava.service.CourtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courts")
public class CourtController {

    private CourtService courtService;

    @Autowired
    public CourtController(CourtService courtService) {
        this.courtService = courtService;
    }

    @GetMapping("/public")
    public List<Court> getPublicCourts() {
        return courtService.getCourtsByType(Court.TYPE_PUBLIC);
    }

    @GetMapping("/private")
    public List<Court> getPrivateCourts() {
        return courtService.getCourtsByType(Court.TYPE_PRIVATE);
    }

    @GetMapping("/{id}")
    public Court getCourt(@PathVariable Integer id) {
        return courtService.find(id);
    }

    @PostMapping()
    public String addCourt(@RequestBody Court court) {
        courtService.save(court);

        return "Success!";
    }

    @GetMapping("/delete/{id}")
    public String deleteCourt(@PathVariable Integer id) {
        Court court = courtService.find(id);

        if (court == null) {
            return "Court not found!";
        }

        courtService.delete(court);

        return "Success!";
    }
}
