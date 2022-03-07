package miri.pro.springjpaassociationsinheritance;

import miri.pro.springjpaassociationsinheritance.entities.Doctor;
import miri.pro.springjpaassociationsinheritance.repositories.DoctorRepository;
import miri.pro.springjpaassociationsinheritance.repositories.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class SpringJpaAssociationsInheritanceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringJpaAssociationsInheritanceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(
							){
		return args -> {

			Stream.of("Essadeq", "Mariam", "Oumaima", "Ali").
					forEach((name)->{
						Doctor doctor;
						doctor = new Doctor();
						doctor.setName(name);
						doctor.setEmail(name.concat("@gmail.com"));
						doctor.setSpeciality(Stream.of("Dentist", "Cardio", "Psycho").findAny().toString());

					});
		};
	}
}
