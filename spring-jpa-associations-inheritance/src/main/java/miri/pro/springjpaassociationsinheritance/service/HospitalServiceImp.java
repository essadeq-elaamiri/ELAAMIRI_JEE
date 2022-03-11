package miri.pro.springjpaassociationsinheritance.service;

import miri.pro.springjpaassociationsinheritance.entities.Appointment;
import miri.pro.springjpaassociationsinheritance.entities.Doctor;
import miri.pro.springjpaassociationsinheritance.entities.Patient;
import miri.pro.springjpaassociationsinheritance.entities.Visit;
import miri.pro.springjpaassociationsinheritance.repositories.AppointmentRepository;
import miri.pro.springjpaassociationsinheritance.repositories.DoctorRepository;
import miri.pro.springjpaassociationsinheritance.repositories.PatientRepository;
import miri.pro.springjpaassociationsinheritance.repositories.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@Transactional
public class HospitalServiceImp implements IHospitalService{

    //@Autowired
    private PatientRepository patientRepository;
    //@Autowired
    private DoctorRepository doctorRepository;
    //@Autowired
    private VisitRepository visitRepository;
    //@Autowired
    private AppointmentRepository appointmentRepository;

    public HospitalServiceImp(PatientRepository patientRepository,
                              DoctorRepository doctorRepository,
                              VisitRepository visitRepository,
                              AppointmentRepository appointmentRepository)
    {
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.visitRepository = visitRepository;
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public Patient savePatient(Patient patient) {
        // treatment
        return patientRepository.save(patient);
    }

    @Override
    public Doctor saveDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public Visit saveVisit(Visit visit) {
        return visitRepository.save(visit);
    }

    @Override
    public Appointment saveAppointment(Appointment appointment) {
        appointment.setId(UUID.randomUUID().toString()); //generate unique id
        return appointmentRepository.save(appointment);
    }
}
