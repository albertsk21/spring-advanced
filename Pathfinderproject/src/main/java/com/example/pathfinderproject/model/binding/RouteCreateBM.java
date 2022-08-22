package com.example.pathfinderproject.model.binding;

import com.example.pathfinderproject.model.entity.enums.CategoryNameEnum;
import com.example.pathfinderproject.model.entity.enums.UserLevelEnum;
import com.sun.istack.NotNull;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Size;
import java.util.Set;

public class RouteCreateBM {
    private String name;
    private String description;
    private MultipartFile gpxCoordinates;
    private UserLevelEnum level;
    private String videoUrl;
    private Set<CategoryNameEnum> categories;

    public RouteCreateBM() {
    }

    @Size(min = 3, max = 20, message = "Route name must be between 3 and 20 characters.")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Size(min = 3) // temporary
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MultipartFile getGpxCoordinates() {
        return gpxCoordinates;
    }

    public void setGpxCoordinates(MultipartFile gpxCoordinates) {
        this.gpxCoordinates = gpxCoordinates;
    }

    @NotNull
    public UserLevelEnum getLevel() {
        return level;
    }

    public void setLevel(UserLevelEnum level) {
        this.level = level;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public Set<CategoryNameEnum> getCategories() {
        return categories;
    }

    public void setCategories(Set<CategoryNameEnum> categories) {
        this.categories = categories;
    }
}
