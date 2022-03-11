package miri.pro.springjpaassociationsinheritance.repositories;

import miri.pro.springjpaassociationsinheritance.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface AppointmentRepository extends JpaRepository<Appointment, String> {
}
