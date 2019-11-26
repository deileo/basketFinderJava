package com.deileo.basketFinderJava.service;

import com.deileo.basketFinderJava.entity.Court;
import com.deileo.basketFinderJava.entity.CourtType;
import com.deileo.basketFinderJava.payload.CourtDto;
import com.deileo.basketFinderJava.repository.CourtRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CourtServiceImplTest {

    @Autowired
    private CourtServiceImpl courtService;

    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private CourtRepository courtRepo;

    @Test
    public void testShouldReturnListOfCourts() {
        Court court = new Court();
        CourtDto courtDto = new CourtDto();

        List<Court> courts = new ArrayList<>();
        courts.add(court);

        when(courtRepo.findAll()).thenReturn(courts);
        when(modelMapper.map(court, CourtDto.class)).thenReturn(courtDto);

        List<CourtDto> courtList = courtService.findAll();

        assertEquals(1, courtList.size());
        assertEquals(courtList.get(0), courtDto);
    }

    @Test
    public void testShouldSaveCourt() {
        Court court = new Court();

        when(courtRepo.save(court)).thenReturn(court);

        courtService.save(court);
    }

    @Test
    public void testShouldDeleteCourt() {
        Court court = new Court();

        doNothing().when(courtRepo).delete(court);

        courtService.delete(court);
    }

    @Test
    public void testShouldReturnCourtsByType() {
        Court court = new Court();
        CourtDto courtDto = new CourtDto();

        List<Court> courts = new ArrayList<>();
        courts.add(court);

        when(courtRepo.getCourtsByType(CourtType.PUBLIC)).thenReturn(courts);
        when(modelMapper.map(court, CourtDto.class)).thenReturn(courtDto);

        List<CourtDto> courtList = courtService.getCourtsByType(CourtType.PUBLIC);

        assertEquals(1, courtList.size());
        assertEquals(courtDto, courtList.get(0));
    }
}
