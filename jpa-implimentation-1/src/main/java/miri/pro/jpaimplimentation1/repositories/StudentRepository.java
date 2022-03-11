package miri.pro.jpaimplimentation1.repositories;

import miri.pro.jpaimplimentation1.entites.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.util.List;

//JpaRepository<ManagedEntity, PrimaryKeyType>
public interface  StudentRepository extends JpaRepository<Student, Long> {
    // Now without adding any thing, we have the basic functions to deal
    // with the database findAll(), findById(), findAllById() ...

    //adding some functions
    public List<Student> findAllByGraduated(boolean isGraduated);
    //public Page<Student> findAllByGraduated(boolean isGraduated, Pageable pageable);
    public List<Student> findAllByGraduatedAndAgeIsLessThan(boolean isGraduated, int age );
    public  List<Student> findAllByGraduatedAndAgeGreaterThanEqualAndFirstNameContains(
            boolean isGraduated, int age, String str);

    @Query("select student from Student student where student.graduated=:isGraduated and student.age>=:age and student.firstName like %:key%")
    public List<Student> findStudentsWithHQL(@Param("isGraduated") boolean isGraduated, @Param("age") int age, @Param("key") String key);

}
