package com.deileo.basketFinderJava.service;

import com.deileo.basketFinderJava.entity.Court;
import com.deileo.basketFinderJava.repository.CourtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CourtServiceImpl implements CourtService {

    private CourtRepository courtRepo;

    @Autowired
    public CourtServiceImpl(CourtRepository courtRepo) {
        this.courtRepo = courtRepo;
    }

    @Override
    @Transactional
    public List<Court> findAll() {
        return courtRepo.findAll();
    }

    @Override
    @Transactional
    public Court find(Integer id) {
        Optional<Court> court = courtRepo.findById(id);

        return court.orElse(null);
    }

    @Override
    @Transactional
    public void save(Court court) {
        courtRepo.save(court);
    }

    @Override
    @Transactional
    public void delete(Court court) {
        courtRepo.delete(court);
    }

}
