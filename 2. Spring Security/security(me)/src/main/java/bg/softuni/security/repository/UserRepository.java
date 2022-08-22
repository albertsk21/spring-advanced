package bg.softuni.security.repository;

import bg.softuni.security.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {



    @Query("FROM UserEntity u WHERE u.email = ?1")
    Optional<UserEntity> findUserEntityByEmail(String email);
}
