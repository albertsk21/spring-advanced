package bg.softuni.cache.repository;

import bg.softuni.cache.model.StudentDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class StudentRepository {


    private final Logger LOGGER = LoggerFactory.getLogger(StudentRepository.class);
    private final List<StudentDTO> students = new ArrayList<>();


    public StudentRepository() {
        students.add(new StudentDTO("Pesho",21));
        students.add(new StudentDTO("Anna",32));
    }
    public List<StudentDTO> findAllStudents(){
        LOGGER.info("Fetching a student by name in repo...");
        return new ArrayList<>(students);
    }

    public Optional<StudentDTO> findStudentByName(String name){

        return students
                .stream()
                .filter(s -> s.getName().equals(name))
                .findAny();
    }

    public List<StudentDTO> refresh(){
//        past a year
       List<StudentDTO> refreshStudent = students
               .stream()
               .map(s -> s.setAge(s.getAge() + 1))
               .collect(Collectors.toList());

       students.clear();
       students.addAll(refreshStudent);

       return students;
    }
}
