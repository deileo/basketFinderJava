package com.deileo.basketFinderJava.repository;

import com.deileo.basketFinderJava.entity.Court;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourtRepository extends JpaRepository<Court, Integer> {

    @Query("SELECT c FROM Court c WHERE c.type = :type")
    List<Court> getCourtsByType(@Param("type") String type);
}
