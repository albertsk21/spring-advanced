package com.example.pathfinderproject.repository;

import com.example.pathfinderproject.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsernameAndPassword(String username, String password);
    Optional<User> findByUsername(String username);


    @Transactional
    @Modifying
    @Query("DELETE FROM User u WHERE u.id IS NOT NULL")
    void deleteAll();
}