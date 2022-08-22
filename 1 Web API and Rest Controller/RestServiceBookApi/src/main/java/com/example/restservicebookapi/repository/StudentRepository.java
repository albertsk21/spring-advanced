package com.example.restservicebookapi.repository;

import com.example.restservicebookapi.model.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity,Long> {

    @Query("FROM StudentEntity AS s WHERE s.name = ?1")
    Optional<StudentEntity> findByName(String name);




}
