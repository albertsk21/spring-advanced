package bg.softuni.proxies;

import java.lang.reflect.Proxy;

public class Test {

    public static void main(String[] args) {

        StudentServiceIfc studentService = createProxiedService();

        System.out.println(studentService.findAllStudents());
        System.out.println(studentService.findAllStudents());

    }
    private static StudentServiceIfc createProxiedService(){
       return (StudentServiceIfc) Proxy.newProxyInstance(
                Test.class.getClassLoader(),
                new Class[]{StudentServiceIfc.class},
               new CacheableInvocationHandler(new StudentService())
        );
    }
}
