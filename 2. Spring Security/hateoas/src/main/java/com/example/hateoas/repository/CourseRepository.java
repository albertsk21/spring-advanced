package com.example.hateoas.repository;

import com.example.hateoas.model.entity.CourseEntity;
import com.example.hateoas.model.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity,Long> {
}
