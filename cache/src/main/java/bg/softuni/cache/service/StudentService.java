package bg.softuni.cache.service;

import bg.softuni.cache.model.StudentDTO;
import bg.softuni.cache.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final Logger LOGGER = LoggerFactory.getLogger(StudentService.class);
    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    @Cacheable("students")
    public List<StudentDTO> findAllStudents(){
        LOGGER.info("Find all students in service called");
        return studentRepository.findAllStudents();
    }

    @Cacheable("students")
    public Optional<StudentDTO> findStudentByName(String name){
        LOGGER.info("Find student by their name in service called");
        return studentRepository.findStudentByName(name);
    }
    @CachePut("students")
    public List<StudentDTO> refresh(){
        LOGGER.info("Refresh in service called");

        this.clearCache();
        return studentRepository.refresh();
    }


    @CacheEvict(cacheNames = "students", allEntries = true)
    public void clearCache(){}
}
