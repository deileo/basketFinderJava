package com.deileo.basketFinderJava.service;

import com.deileo.basketFinderJava.entity.Court;
import com.deileo.basketFinderJava.entity.CourtType;
import com.deileo.basketFinderJava.response.CourtDto;

import java.util.List;

public interface CourtService {

    public List<CourtDto> findAll();

    public CourtDto find(Integer id);

    public void save(Court court);

    public void delete(Court court);

    public List<CourtDto> getCourtsByType(CourtType type);
}
