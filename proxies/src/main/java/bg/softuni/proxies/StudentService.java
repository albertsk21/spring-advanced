package bg.softuni.proxies;

import java.util.List;

public class StudentService implements StudentServiceIfc{

//    @Cacheable("students")
    @Override
    public List<Student> findAllStudents() {

        System.out.println("In findAllStudents method");
        try {
            Thread.sleep(2000);
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
        return List.of(
                new Student("Anna", 21),
                new Student("Pesho", 31)
        );
    }
}
