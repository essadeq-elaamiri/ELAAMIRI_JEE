package com.elaamiri.patientsmanagement.repositories;

import com.elaamiri.patientsmanagement.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, String> {
    Page<Patient> findAllByFirstNameContainsOrLastNameContains(String keyWord, PageRequest pageRequest);
    //Page<Patient> findAllByFirstNameContainsOrLastNameContains(String keyWord, Pageable pageable);
    /*
    org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name
    'patientController' defined in file [C:\Users\Essadeq\IdeaProjects\JavaEE_S4\patients-management\
    target\classes\com\elaamiri\patientsmanagement\controllers\PatientController.class]: Unsatisfied dependency
     expressed through constructor parameter 0; nested exception is org.springframework.beans.factory.
     UnsatisfiedDependencyException: Error creating bean with name 'patientServiceImp' defined in file
     [C:\Users\Essadeq\IdeaProjects\JavaEE_S4\patients-management\target\classes\com\elaamiri\patientsmanagement
     \services\PatientServiceImp.class]: Unsatisfied dependency expressed through constructor parameter 0; nested
      exception is org.springframework.beans.factory.BeanCreationException: Error creating bean with name
      'patientRepository' defined in com.elaamiri.patientsmanagement.repositories.PatientRepository defined
        .....
     */
}
