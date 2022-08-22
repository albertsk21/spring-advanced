package com.example.pathfinderproject.model.view;

import com.example.pathfinderproject.model.entity.enums.UserLevelEnum;

public class UserVM {
    private Long id;
    private String fullName;
    private String username;
    private Integer age;
    private UserLevelEnum level;
    public UserVM() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public UserLevelEnum getLevel() {
        return level;
    }

    public void setLevel(UserLevelEnum level) {
        this.level = level;
    }
}
