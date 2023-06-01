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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public List<Address> gerAddressesByCustomerId(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'gerAddressesByCustomerId'");
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
