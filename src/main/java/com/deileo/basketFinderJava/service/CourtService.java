package com.deileo.basketFinderJava.service;

import com.deileo.basketFinderJava.entity.Court;

import java.util.List;

public interface CourtService {

    public List<Court> findAll();

    public Court find(Integer id);

    public void save(Court court);

    public void delete(Court court);
}
