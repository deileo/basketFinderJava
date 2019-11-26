package com.deileo.basketFinderJava.service;

import com.deileo.basketFinderJava.entity.Court;
import com.deileo.basketFinderJava.entity.CourtType;
import com.deileo.basketFinderJava.payload.CourtDto;

import java.util.List;

public interface CourtService {

    List<CourtDto> findAll();

    CourtDto find(Integer id);

    void save(Court court);

    void delete(Court court);

    List<CourtDto> getCourtsByType(CourtType type);
}
