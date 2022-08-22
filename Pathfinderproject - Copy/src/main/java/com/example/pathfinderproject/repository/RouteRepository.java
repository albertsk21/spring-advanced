package com.example.pathfinderproject.repository;

import com.example.pathfinderproject.model.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {


    @Query("FROM Route r WHERE r.name = ?1")
    Optional<Route> findByName(String name);

    @Modifying
    @Transactional
    @Query("DELETE FROM Route ")
    void deleteAll();
}
