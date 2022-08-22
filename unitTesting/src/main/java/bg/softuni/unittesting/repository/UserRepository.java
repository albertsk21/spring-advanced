package bg.softuni.unittesting.repository;

import bg.softuni.unittesting.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    @Query("FROM User AS u where u.username = ?1")
    Optional<User> findByUsername(String username);
}
