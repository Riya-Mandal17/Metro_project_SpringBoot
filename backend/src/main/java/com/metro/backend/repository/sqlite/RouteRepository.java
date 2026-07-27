package com.metro.backend.repository.sqlite;

import com.metro.backend.entity.sqlite.Connections;
import com.metro.backend.entity.sqlite.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<Station,Integer>  {

    @Query(""" 
            SELECT s1.name,s2.name,c.travel_time_minutes,c.fare_inr,i.transfer_time_minutes FROM Connections c JOIN c.station_a_id s1
             JOIN c.station_b_id s2 LEFT JOIN Interchanges i  ON i.station_from_id = s1  AND i.station_to_id = s2""")

    List<Connections> getConnections();

}
