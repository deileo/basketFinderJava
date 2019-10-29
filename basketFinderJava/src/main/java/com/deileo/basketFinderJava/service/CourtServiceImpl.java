package com.deileo.basketFinderJava.service;

import com.deileo.basketFinderJava.entity.Court;
import com.deileo.basketFinderJava.entity.CourtType;
import com.deileo.basketFinderJava.repository.CourtRepository;
import com.deileo.basketFinderJava.payload.CourtDto;
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

    @Autowired
    private CourtRepository courtRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<CourtDto> findAll() {
        List<CourtDto> courts = new ArrayList<>();
        courtRepo.findAll().forEach(court -> courts.add(convertToDto(court)));

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
        courtRepo.getCourtsByType(type).forEach(court -> courts.add(convertToDto(court)));

        return courts;
    }

    private CourtDto convertToDto(Court court) {
        CourtDto courtDto = modelMapper.map(court, CourtDto.class);

        courtDto.setCommentsCount(court.getComments().size());

        return courtDto;
    }
}
