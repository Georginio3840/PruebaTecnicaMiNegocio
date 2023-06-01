package com.prueba.prueba.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.prueba.prueba.model.Address;
import com.prueba.prueba.model.Customer;
import com.prueba.prueba.repositories.CustomerRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Transactional
@Service
@AllArgsConstructor
public class CustomerService implements ICustomerService {

    private final CustomerRepository customerRepository;
    @Override
    public List<Customer> findAll() {
    return customerRepository.findAll();  
    }

    @Override
    public Customer create(Customer customer) {
        return customerRepository.save(customer);
    }

  

    @Override
    public void delete(Long id) {
        customerRepository.deleteById(id);
    
    }


    @Override
    public List<Customer> findByNameOrDni(String name, String dni) {
        return customerRepository.findByNameOrDniCustomer(name, dni);    
    }

    @Override
    public Customer update(Customer customer) {
        return customerRepository.save(customer);   
    }

    
    
}
