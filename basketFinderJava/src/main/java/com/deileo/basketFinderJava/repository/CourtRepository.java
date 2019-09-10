package com.deileo.basketFinderJava.repository;

import com.deileo.basketFinderJava.entity.Court;
import com.deileo.basketFinderJava.entity.CourtType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourtRepository extends JpaRepository<Court, Integer> {

    @Query("SELECT c FROM Court c WHERE c.type = :type")
    List<Court> getCourtsByType(@Param("type") CourtType type);
}
