package com.example.pathfinderproject.repository;

import com.example.pathfinderproject.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {


    @Query("FROM Comment c WHERE c.textContent = ?1")
    Optional<Comment> findByTextContent(String textContent);
}