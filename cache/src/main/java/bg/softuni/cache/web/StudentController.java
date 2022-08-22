package bg.softuni.cache.web;

import bg.softuni.cache.model.StudentDTO;
import bg.softuni.cache.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private StudentService studentService;
    private final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("")
    public ResponseEntity<List<StudentDTO>> getAllStudents(){

        var students = studentService.findAllStudents();
        LOGGER.info("find all endpoint called, All students: {}",students);
        return ResponseEntity.ok(students);
    }

    @GetMapping("/find-by-name")
    public ResponseEntity<StudentDTO> getStudentByName(@RequestParam(name = "name", defaultValue = "") String name){
        var studentOptional = studentService.findStudentByName(name);
        LOGGER.info("find by name endpoint called, All student: {}",studentOptional.get());
        if(studentOptional.isEmpty()){
            return ResponseEntity
                    .notFound()
                    .build();
        }
        return ResponseEntity.ok(studentOptional.get());
    }


    @GetMapping("/refresh")
    public ResponseEntity<List<StudentDTO>> refreshPage(){
        var students = studentService.refresh();
        LOGGER.info("refresh students endpoint called, All students: {}",students);
        return ResponseEntity.ok(students);
    }



}
