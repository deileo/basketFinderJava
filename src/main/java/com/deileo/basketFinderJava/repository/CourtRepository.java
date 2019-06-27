package com.deileo.basketFinderJava.repository;

import com.deileo.basketFinderJava.entity.Court;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourtRepository extends JpaRepository<Court, Integer> {
}
