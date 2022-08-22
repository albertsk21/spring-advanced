package bg.softuni.errors.web;

import bg.softuni.errors.student.StudentDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentRESTController {

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable(name = "id") String id){
        throw new StudentNotFoundException(id);
    }


    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler({StudentNotFoundException.class})
    public RestErrorInfo handelNotFoundException(StudentNotFoundException sne){
        return new RestErrorInfo(sne.getId(),"Student not found!");
    }

}
