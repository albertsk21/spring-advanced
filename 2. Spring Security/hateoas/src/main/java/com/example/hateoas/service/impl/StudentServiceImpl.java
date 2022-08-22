package com.example.hateoas.service.impl;

import com.example.hateoas.model.dto.OrderDTO;
import com.example.hateoas.model.dto.StudentDTO;
import com.example.hateoas.model.entity.OrderEntity;
import com.example.hateoas.model.entity.StudentEntity;
import com.example.hateoas.repository.StudentRepository;
import com.example.hateoas.service.StudentService;
import com.example.hateoas.util.error.ObjectNotFoundException;
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
    public Optional<StudentDTO> findById(Long id) {
        var studentOpt = this.studentRepository.findById(id);
        if(studentOpt.isEmpty()) {
           return Optional.empty();
        }
        return Optional.of(this.map(studentOpt.get()));
    }

    @Override
    public List<StudentDTO> getAll() {
        return this.studentRepository
                .findAll()
                .stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    @Override
    public StudentDTO addStudent(StudentDTO studentDTO) {
        StudentEntity student = new StudentEntity()
                .setAge(studentDTO.getAge())
                .setDeleted(studentDTO.isDeleted())
                .setName(studentDTO.getName());
       student =  this.studentRepository.save(student);

       return this.map(student);
    }

    private StudentDTO map(StudentEntity student){
        return new StudentDTO()
                .setId(student.getId())
                .setDeleted(student.isDeleted())
                .setName(student.getName())
                .setOrders(
                        student
                                .getOrders()
                                .stream()
                                .map(this::mapOrderDto)
                                .collect(Collectors.toList())
                );
    }

    private OrderDTO mapOrderDto(OrderEntity order){
        return new OrderDTO()
                .setId(order.getId())
                .setCourseId(order.getCourse().getId())
                .setStudentId(order.getStudent().getId());
    }

}
