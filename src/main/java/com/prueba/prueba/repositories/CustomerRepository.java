package com.prueba.prueba.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prueba.prueba.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByNameOrDniCustomer(String name, String dniCustomer);
    Optional<Customer> findByDniCustomer(String dniCustomer);

}
