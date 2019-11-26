package com.deileo.basketFinderJava.service;

import com.deileo.basketFinderJava.entity.Court;
import com.deileo.basketFinderJava.entity.CourtType;
import com.deileo.basketFinderJava.repository.CourtRepository;
import com.deileo.basketFinderJava.payload.CourtDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CourtServiceImpl implements CourtService {

    private final CourtRepository courtRepo;

    private final ModelMapper modelMapper;

    @Autowired
    public CourtServiceImpl(CourtRepository courtRepo, ModelMapper modelMapper) {
        this.courtRepo = courtRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CourtDto> findAll() {
        return courtRepo.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
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
        return courtRepo.getCourtsByType(type)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private CourtDto convertToDto(Court court) {
        CourtDto courtDto = modelMapper.map(court, CourtDto.class);

        courtDto.setCommentsCount(court.getComments().size());

        return courtDto;
    }
}
