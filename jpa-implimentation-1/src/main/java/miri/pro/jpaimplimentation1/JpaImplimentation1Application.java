package miri.pro.jpaimplimentation1;

import miri.pro.jpaimplimentation1.entites.Student;
import miri.pro.jpaimplimentation1.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.sql.Date;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

@SpringBootApplication
public class JpaImplimentation1Application implements CommandLineRunner {
    //Dependency injection
    /*
     means: please find me in implementation of this interface
     and inject it here in this field
     Spring will take care of that, it creates the implementation
     by itself,
     */
    @Autowired
    private StudentRepository studentRepository;
    public static void main(String[] args) {
        SpringApplication.run(
                JpaImplimentation1Application.class,
                args);
    }
    @Override
    public void run(String... args) throws Exception {
        //letsTestPagination();
        testingAddedFunctions();
    }

    private void testingAddedFunctions(){
        //adding random 100 record
        for (int i=1; i<=100; i++){
            studentRepository.save(
                    new Student(null
                            , "fname_"+i
                            , "Lname_"+i
                            , new java.util.Date()
                            , (int)(Math.random()*100)+1
                            , (int)(Math.random()*2) != 0));
        }

        //
        System.out.println("All non graduated students ====================");
        List<Student> studentList = studentRepository.findAllByGraduated(false);
        displayList(studentList);

        System.out.println("All  graduated and under 15 students ====================");
        List<Student> studentList2 = studentRepository.findAllByGraduatedAndAgeIsLessThan(true, 15);
        displayList(studentList2);

        System.out.println("All  graduated and  greater than 20 yo and names contain '5' char students ====================");
        List<Student> studentList3 = studentRepository.findAllByGraduatedAndAgeGreaterThanEqualAndFirstNameContains(true, 20, "5");
        displayList(studentList3);

        System.out.println("using HQl get All  graduated and  greater than 20 yo and names contain '5' char students ====================");
        List<Student> studentList4 = studentRepository.findStudentsWithHQL(true, 20, "5");
        displayList(studentList4);

    }
    private void letsTestPagination(){
        //adding random 100 record
        for (int i=1; i<=100; i++){
            studentRepository.save(
                    new Student(null
                            , "fname_"+i
                            , "Lname_"+i
                            , new java.util.Date()
                            , (int)(Math.random()*100)+1
                            , (int)(Math.random()*2) != 0));
        }

        // pagination
        Page<Student> studentPage = studentRepository.findAll(PageRequest.of(2, 10));
        System.out.println("Total pages: "+ studentPage.getTotalPages());
        System.out.println("Total elements: "+ studentPage.getTotalElements());
        System.out.println("Page number: "+ studentPage.getNumber());
        List<Student> studentList = studentPage.getContent();
        displayList(studentPage);

    }

    private void testingBasicJPAFunctions(){

        // using DB functions
        studentRepository.save(
                new Student(null
                        , "Essadeq"
                        , "Elaamiri"
                        , new Date(Date.valueOf("1999-01-07").getTime())
                        , 23
                        , false));
        studentRepository.save(
                new Student(null
                        , "khalid"
                        , "Elaamiri"
                        , new Date(Date.valueOf("2003-01-07").getTime())
                        , 12
                        , true));
        studentRepository.save(
                new Student(null
                        , "fatima"
                        , "Elaamiri"
                        , new Date(Date.valueOf("2007-01-07").getTime())
                        , 13
                        , false));
        studentRepository.save(
                new Student(null
                        , "trye"
                        , "Elaamiri"
                        , new Date(Date.valueOf("1999-01-07").getTime())
                        , 28
                        , true));
        studentRepository.save(
                new Student(null
                        , "Essadeq"
                        , "Elaamiri"
                        , new Date(Date.valueOf("1999-01-07").getTime())
                        , 65
                        , true));

        studentRepository.save(
                new Student(null
                        , "Essadeq"
                        , "Test"
                        , new Date(Date.valueOf("1999-01-07").getTime())
                        , 34
                        , true));
        studentRepository.save(
                new Student(null
                        , "hafssa"
                        , "Elaamiri"
                        , new Date(Date.valueOf("1999-01-07").getTime())
                        , 2
                        , false));

        // retrieve all
        List<Student> studentList = studentRepository.findAll();
        displayList(studentList);

        System.out.println(")==========================(");
        // one by id
        Student student =  studentRepository.findById(4L).get();
        System.out.println(student.toString());
        //if not exist return null
        Student student2 =  studentRepository.findById(5L).orElse(null);
        System.out.println(student2.toString());
        //Student student3 =  studentRepository.findById(30L).orElseThrow(()-> new RuntimeException("Can not found !"));
        //System.out.println(student3.toString());
        student2.setAge(100); // upadate object
        studentRepository.save(student2); // save updated object => update entry in the DB

        System.out.println(")==========================(");
        displayList(studentRepository.findAll());

        studentRepository.deleteById(7L);

    }
    private void displayList(Iterable it){
        it.forEach(ob->{
            System.out.println(ob.toString());
        });
    }
}
