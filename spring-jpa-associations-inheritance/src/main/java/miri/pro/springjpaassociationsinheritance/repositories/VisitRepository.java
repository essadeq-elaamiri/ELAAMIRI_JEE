package miri.pro.springjpaassociationsinheritance.repositories;

import miri.pro.springjpaassociationsinheritance.entities.Appointment;
import miri.pro.springjpaassociationsinheritance.entities.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


public interface VisitRepository extends JpaRepository<Visit, Long> {
}
