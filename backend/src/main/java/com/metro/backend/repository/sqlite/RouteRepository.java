package com.metro.backend.repository.sqlite;

import com.metro.backend.entity.sqlite.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends JpaRepository<Station,Integer>  {

}
