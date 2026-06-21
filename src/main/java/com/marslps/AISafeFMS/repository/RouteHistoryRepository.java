package com.marslps.AISafeFMS.repository;

import com.marslps.AISafeFMS.model.entities.RouteHistory;
import com.marslps.AISafeFMS.model.vo.RouteID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RouteHistoryRepository extends JpaRepository<RouteHistory, Long> {

    @Query("select h from RouteHistory h where h.route.id = :routeId")
    List<RouteHistory> findByRouteId(@Param("routeId") RouteID routeId);
}