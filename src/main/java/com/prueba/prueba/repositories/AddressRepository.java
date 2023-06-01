package com.prueba.prueba.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prueba.prueba.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByCustomerId(Long customerId);
}

