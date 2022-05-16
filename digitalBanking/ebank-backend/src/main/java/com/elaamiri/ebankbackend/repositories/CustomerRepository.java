package com.elaamiri.ebankbackend.repositories;

import com.elaamiri.ebankbackend.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
}
