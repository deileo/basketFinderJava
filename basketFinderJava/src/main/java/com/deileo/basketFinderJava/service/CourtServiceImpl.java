package com.deileo.basketFinderJava.service;

import com.deileo.basketFinderJava.entity.Court;
import com.deileo.basketFinderJava.entity.CourtType;
import com.deileo.basketFinderJava.repository.CourtRepository;
import com.deileo.basketFinderJava.response.CourtDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CourtServiceImpl implements CourtService {

    private CourtRepository courtRepo;

    private ModelMapper modelMapper;

    @Autowired
    public CourtServiceImpl(CourtRepository courtRepo, ModelMapper modelMapper) {
        this.courtRepo = courtRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CourtDto> findAll() {
        List<CourtDto> courts = new ArrayList<>();
        for (Court court : courtRepo.findAll()) {
            courts.add(convertToDto(court));
        }

        return courts;
    }

    @Override
    public CourtDto find(Integer id) {
        Optional<Court> court = courtRepo.findById(id);

        return court.map(this::convertToDto).orElse(null);

    }

    @Override
    public void save(Court court) {
        courtRepo.save(court);
    }

    @Override
    @Transactional
    public void delete(Court court) {
        courtRepo.delete(court);
    }

    @Override
    public List<CourtDto> getCourtsByType(CourtType type) {
        List<CourtDto> courts = new ArrayList<>();
        for (Court court : courtRepo.getCourtsByType(type)) {
            courts.add(convertToDto(court));
        }

        return courts;
    }

    private CourtDto convertToDto(Court court) {
        return modelMapper.map(court, CourtDto.class);
    }
}
