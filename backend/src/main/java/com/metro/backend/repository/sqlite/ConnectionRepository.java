package com.metro.backend.repository.sqlite;

import com.metro.backend.entity.sqlite.Connections;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConnectionRepository extends JpaRepository<Connections,Integer> {
}
