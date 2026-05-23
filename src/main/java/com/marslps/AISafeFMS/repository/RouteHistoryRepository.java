package com.marslps.AISafeFMS.repository;

import com.marslps.AISafeFMS.model.entities.RouteHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RouteHistoryRepository extends JpaRepository<RouteHistory, Long> {

    List<RouteHistory> findByRoute_Id_Id(String routeId);
}