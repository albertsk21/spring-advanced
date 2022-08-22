package com.example.hateoas.repository;

import com.example.hateoas.model.entity.OrderEntity;
import com.example.hateoas.model.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StudentRepository extends JpaRepository<StudentEntity,Long> {
}
