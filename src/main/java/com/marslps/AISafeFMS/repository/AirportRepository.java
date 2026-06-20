package com.marslps.AISafeFMS.repository;

import com.marslps.AISafeFMS.model.entities.Airport;
import com.marslps.AISafeFMS.model.vo.LocationIdentifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AirportRepository extends CrudRepository<Airport, Long> {
    Optional<Airport> findByIata(LocationIdentifier iata);

    List<Airport> findByName(String name);
    @Query("""
         SELECT a FROM Airport a 
         WHERE a.airport_location.city = :city
     """)
    List<Airport> findByCity(@Param("city") String city);

    @Query("""
         SELECT a FROM Airport a 
         WHERE a.airport_location.country = :country
     """)
    List<Airport> findByCountry(@Param("country") String country);

    @Query("""
         SELECT a FROM Airport a 
         WHERE a.airport_location.region = :region 
         ORDER BY a.airport_location.country, a.name
     """)
    List<Airport> groupByRegion(@Param("region") String region);

    @Query("""
         SELECT a FROM Airport a 
         WHERE a.airport_location.country = :country 
         ORDER BY a.airport_location.region, a.name
     """)
    List<Airport> groupByCountry(@Param("country") String country);
}
