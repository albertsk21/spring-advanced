package bg.softuni.hateoas.service;

import bg.softuni.hateoas.model.dto.StudentDTO;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface StudentService {
    Optional<StudentDTO> findStudentById(Long studentId);
    List<StudentDTO> findAllStudents();

}
