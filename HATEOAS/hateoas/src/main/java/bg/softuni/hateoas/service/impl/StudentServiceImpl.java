package bg.softuni.hateoas.service.impl;

import bg.softuni.hateoas.model.dto.OrderDTO;
import bg.softuni.hateoas.model.dto.StudentDTO;
import bg.softuni.hateoas.model.entity.OrderEntity;
import bg.softuni.hateoas.model.entity.StudentEntity;
import bg.softuni.hateoas.repository.StudentRepository;
import bg.softuni.hateoas.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Optional<StudentDTO> findStudentById(Long studentId) {

       return this.studentRepository
                .findById(studentId)
                .map(this::map);
    }

    @Override
    public List<StudentDTO> findAllStudents() {


        return this.studentRepository
                .findAll()
                .stream()
                .map(this::map)
                .collect(Collectors.toList());
    }


    private StudentDTO map(StudentEntity student){

        return new StudentDTO()
                .setAge(student.getAge())
                .setDeleted(student.isDeleted())
                .setName(student.getName())
                .setId(student.getId())
                .setOrders(

                        student
                        .getOrders()
                        .stream()
                        .map(this::mapOrder)
                        .collect(Collectors.toList())

                );
    }


    private OrderDTO mapOrder(OrderEntity order){
        return new OrderDTO()
                .setCourseId(order.getCourse().getId())
                .setId(order.getId())
                .setStudentId(order.getStudent().getId());
    }
}
