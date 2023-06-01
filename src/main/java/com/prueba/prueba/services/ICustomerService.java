package com.prueba.prueba.services;

import java.util.List;


import com.prueba.prueba.model.Address;
import com.prueba.prueba.model.Customer;

public interface ICustomerService {
    public List<Customer> findAll();
    public Customer create(Customer customer);
    public Customer update(Customer customer);
    public List <Customer> findByNameOrDni(String name, String dni);
    void delete(Long id);
    public List<Address> gerAddressesByCustomerId(Long id);
}
