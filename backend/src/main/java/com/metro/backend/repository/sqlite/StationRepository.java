package com.metro.backend.repository.sqlite;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.metro.backend.entity.sqlite.Station;

@Repository
public interface StationRepository extends JpaRepository<Station, Integer> {

}