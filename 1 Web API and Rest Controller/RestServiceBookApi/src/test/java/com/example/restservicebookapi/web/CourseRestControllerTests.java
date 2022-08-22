package com.example.restservicebookapi.web;

import com.example.restservicebookapi.model.entity.CourseEntity;
import com.example.restservicebookapi.repository.CourseRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.event.annotation.AfterTestMethod;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CourseRestControllerTests {

    private final String COURSE_JAVASCRIPT = "JavaScript" ;
    private final String COURSE_JAVA_BASICS = "Java Basics" ;
    private final String COURSE_JAVA_FUNDAMENTALS = "Java Fundamentals" ;
    private final int UNKNOWN_ID = 23232;

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private MockMvc mockMvc;


        @BeforeEach
        public void init(){

        this.courseRepository.deleteAll();

        CourseEntity javaScript = new CourseEntity();
        javaScript.setId(Long.parseLong("1"));
        javaScript.setName(COURSE_JAVASCRIPT);
        javaScript.setCreated(LocalDateTime.now());

        CourseEntity javaBasics = new CourseEntity();
        javaBasics.setId(Long.parseLong("2"));;
        javaBasics.setName(COURSE_JAVA_BASICS);
        javaBasics.setCreated(LocalDateTime.now());

        CourseEntity javaFundamentals = new CourseEntity();
        javaFundamentals.setId(Long.parseLong("3"));
        javaFundamentals.setName(COURSE_JAVA_FUNDAMENTALS);
        javaFundamentals.setCreated(LocalDateTime.now());

        this.courseRepository.saveAll(List.of(javaScript, javaBasics, javaFundamentals));
        System.out.println("Before each method");

    }

    @AfterEach
    public void deleteAll(){

        this.courseRepository.deleteAll();
        System.out.println("After each method");

    }

    @Test
    public void shouldGetAllCoursesCorrectly() throws Exception {

       this.mockMvc.perform(get("/api/v1/app/courses"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$.[0].name", is(COURSE_JAVASCRIPT)))
                .andExpect(jsonPath("$.[1].name", is(COURSE_JAVA_BASICS)))
                .andExpect(jsonPath("$.[2].name", is(COURSE_JAVA_FUNDAMENTALS)));
    }

    @Test
    @Order(1)
    public void getCourseByIdShouldReturnAnObject() throws Exception{
        this.mockMvc.perform(get("/api/v1/app/courses/" +1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",is(COURSE_JAVASCRIPT)));
    }

    @Test
    public void getCourseByIdShould_Status_404_NotFound() throws Exception {

        this.mockMvc.perform(get("/api/v1/app/courses/{id}", UNKNOWN_ID))
                .andExpect(status().isNotFound());
    }

    @Test
    public void createCourse_ShouldBeReturnStatusCreated() throws Exception {

        String nameTestCourse = "Test";
        String courseJson  =  String.format("{\"name\": \"%s\"}", nameTestCourse);

        this.mockMvc.perform(post("/api/v1/app/courses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(courseJson))
                .andExpect(status().isCreated());

        this.mockMvc.perform(get("/api/v1/app/courses"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$.[0].name", is(COURSE_JAVASCRIPT)))
                .andExpect(jsonPath("$.[1].name", is(COURSE_JAVA_BASICS)))
                .andExpect(jsonPath("$.[2].name", is(COURSE_JAVA_FUNDAMENTALS)))
                .andExpect(jsonPath("$.[3].name", is(nameTestCourse)));

    }

    @Test
    public void createCourse_StatusBadRequest() throws Exception {
        String courseJson  =  String.format("{" +
                "\"id\": \"%d\"" +
                "\"name\": \"%s\"" +
                "}", 9,COURSE_JAVASCRIPT);
        this.mockMvc.perform(post("/api/v1/app/courses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(courseJson))
                .andExpect(status().isBadRequest());
    }



    @Test
    public void deleteCourseById_ShouldReturnStatusNoContent() throws Exception {


        CourseEntity course = this.courseRepository.findCourseEntityByName(COURSE_JAVASCRIPT).get();

        this.mockMvc.perform(delete("/api/v1/app/courses/" + course.getId()))
                .andExpect(status().isNoContent());

    }



    @Test
    public void deleteCourseById_ShouldReturnStatusNotFound() throws Exception {
        this.mockMvc.perform(delete("/api/v1/app/courses/" + UNKNOWN_ID))
                .andExpect(status().isNotFound());
    }




    @Test
    public void updateStudentById_ShouldReturnStatusNotFound() throws Exception {
        String nameModified = "Tester";
        String courseJson  =  String.format("{\"name\": \"%s\"}", nameModified);


        this.mockMvc.perform(put("/api/v1/app/courses/" + UNKNOWN_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(courseJson))
                .andExpect(status().isNotFound());

    }


    @Test
    public void updateStudentById_ShouldReturnStatusOk() throws Exception {
        CourseEntity course = this.courseRepository.findCourseEntityByName(COURSE_JAVASCRIPT).get();


        String nameModified = "Tester";
        String courseJson  =  String.format("{\"name\": \"%s\"}", nameModified);
        int courseId = 1;



        this.mockMvc.perform(put("/api/v1/app/courses/" + course.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(courseJson))
                .andExpect(status().isOk());

        this.mockMvc.perform(get("/api/v1/app/courses/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].name",is(nameModified)));



    }




}