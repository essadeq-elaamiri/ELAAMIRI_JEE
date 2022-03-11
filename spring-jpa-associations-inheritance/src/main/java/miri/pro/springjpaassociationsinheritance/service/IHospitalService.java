package miri.pro.springjpaassociationsinheritance.service;

import miri.pro.springjpaassociationsinheritance.entities.Appointment;
import miri.pro.springjpaassociationsinheritance.entities.Doctor;
import miri.pro.springjpaassociationsinheritance.entities.Patient;
import miri.pro.springjpaassociationsinheritance.entities.Visit;

public interface IHospitalService {
    Patient savePatient(Patient patient);
    Doctor saveDoctor(Doctor doctor);
    Visit saveVisit(Visit visit);
    Appointment saveAppointment(Appointment appointment);
}
