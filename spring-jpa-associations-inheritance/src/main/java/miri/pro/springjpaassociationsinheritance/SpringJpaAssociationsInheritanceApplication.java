package miri.pro.springjpaassociationsinheritance;

import miri.pro.springjpaassociationsinheritance.entities.*;
import miri.pro.springjpaassociationsinheritance.repositories.AppointmentRepository;
import miri.pro.springjpaassociationsinheritance.repositories.DoctorRepository;
import miri.pro.springjpaassociationsinheritance.repositories.PatientRepository;
import miri.pro.springjpaassociationsinheritance.repositories.VisitRepository;
import miri.pro.springjpaassociationsinheritance.service.IHospitalService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

@SpringBootApplication
public class SpringJpaAssociationsInheritanceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringJpaAssociationsInheritanceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(IHospitalService hospitalService,
							DoctorRepository doctorRepository){
		List<String> list = new ArrayList<String>();
		Stream.of("anesthésie-réanimation", "gynécologie obstétrique", "neurochirurgie", "ophtalmologie", "pédiatrie", "pneumologie").forEach(arg->{
			list.add(arg);
		});

		return args -> {
			Stream.of("Essadeq", "Mariam", "Oumaima", "Ali", "Salma", "Ahmed").
					forEach((name)->{
						Doctor doctor;
						doctor = new Doctor();
						doctor.setName(name);
						doctor.setEmail(name.concat("@gmail.com"));
						doctor.setSpeciality(list.get((new Random()).nextInt(list.size())));
						doctorRepository.save(doctor);
					});

			//Patients
			Stream.of("Essadeq", "Mariam", "Oumaima", "Ali", "Salma", "Ahmed").forEach(p->{
				Patient patient = new Patient();
				patient.setName(p);
				patient.setEmail(p.concat("@gmail.com"));
				patient.setBirth(new Date());
				hospitalService.savePatient(patient);

				// adding appointment
				Appointment appointment = new Appointment();
				appointment.setCanceled(false);
				appointment.setDate(new Date());
				appointment.setStatus(AppointmentStatus.PENDING);
				appointment.setDoctor(doctorRepository.findDoctorByName(p));
				appointment.setPatient(patient);
				hospitalService.saveAppointment(appointment);

				Visit visit = new Visit();
				visit.setVisitDate(new Date());
				visit.setConsultationReport("Report of "+p);
				visit.setAppointment(appointment);
				hospitalService.saveVisit(visit);



			});



		};
	}
	/*
	@Bean
	 CommandLineRunner run(DoctorRepository doctorRepository){
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				Stream.of("Essadeq", "Mariam", "Oumaima", "Ali").
						forEach((name)->{
							Doctor doctor;
							doctor = new Doctor();
							doctor.setName(name);
							doctor.setEmail(name.concat("@gmail.com"));
							doctor.setSpeciality(Stream.of("Dentist", "Cardio", "Psycho").findAny().toString());
							//doctorRepository.

				});
			}
		};
	}*/
}
