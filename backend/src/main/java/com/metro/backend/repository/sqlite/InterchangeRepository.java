package com.metro.backend.repository.sqlite;

import com.metro.backend.entity.sqlite.Interchanges;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterchangeRepository extends JpaRepository<Interchanges,Integer> {
}
