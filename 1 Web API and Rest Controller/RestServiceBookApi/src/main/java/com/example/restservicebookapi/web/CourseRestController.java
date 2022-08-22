package com.example.restservicebookapi.web;

import com.example.restservicebookapi.model.dto.CourseDTO;
import com.example.restservicebookapi.service.CourseService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/app/courses")
public class CourseRestController {

    private CourseService courseService;

    public CourseRestController(CourseService courseService) {
        this.courseService = courseService;
    }

    @Tag(name = "200",description = "You will get all the courses an status is 200 ok")
    @GetMapping("")
    public ResponseEntity<List<CourseDTO>> getAll(){
        return ResponseEntity.ok(this.courseService.getAll());
    }
    @Tag(name = "201",description = "If Object exist will return de status 201 ok and replace the object")
    @PostMapping("")
    public ResponseEntity<CourseDTO> createCourse(@RequestBody CourseDTO courseDTO){
       courseDTO =  this.courseService.saveCourse(courseDTO);
        return ResponseEntity.created(URI.create("/api/v1/app/courses/" + courseDTO.getId())).build();
    }
    @Parameter(name = "id",description = " you need Id Course to replace the object")
    @Tag(name = "200",description = "If Object exist will return de status 200 ok and replace the object")
    @Tag(name = "404",description = "If Object dosen't exist will return de status 404 not found")
    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> updateCourse( @PathVariable(name = "id") Long id, @RequestBody CourseDTO courseDTO){
        courseDTO.setId(id);
        return ResponseEntity.ok(this.courseService.saveCourse(courseDTO));
    }
    @Tag(name = "204",description = "If Object exist will return de status 204 no content and delete the object")
    @Tag(name = "404",description = "If Object dosen't exist will return de status 404 not found")
    @Parameter(name = "id",description = " you need Id Course to delete the object")
    @DeleteMapping("/{id}")
    public ResponseEntity<CourseDTO> deleteCourseById( @PathVariable(name = "id") Long id){
        this.courseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Tag(name = "404",description = "If Object dosen't exist will return de status 404 not found")
    @Parameter(name = "id",description = " you need Id Course to get the object")
    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getCourseByID(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(this.courseService.findById(id));
    }

}
