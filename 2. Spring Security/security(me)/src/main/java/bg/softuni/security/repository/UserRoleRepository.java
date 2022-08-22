package bg.softuni.security.repository;

import bg.softuni.security.model.entity.UserRoleEntity;
import bg.softuni.security.model.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity,Long> {


    @Query("FROM UserRoleEntity ur WHERE  ur.role = ?1")
    Optional<UserRoleEntity> findByRole(UserRoleEnum role);
}
