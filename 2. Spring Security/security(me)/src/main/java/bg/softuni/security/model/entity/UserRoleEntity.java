package bg.softuni.security.model.entity;

import bg.softuni.security.model.enums.UserRoleEnum;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class UserRoleEntity extends BaseEntity{


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRoleEnum role;


    public UserRoleEntity() {
    }

    public UserRoleEnum getRole() {
        return role;
    }

    public UserRoleEntity setRole(UserRoleEnum role) {
        this.role = role;
        return this;
    }


}
