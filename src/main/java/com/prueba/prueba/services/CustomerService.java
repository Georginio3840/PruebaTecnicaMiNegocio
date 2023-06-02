package com.prueba.prueba.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.prueba.prueba.exceptions.DuplicateEntityException;
import com.prueba.prueba.exceptions.DuplicatedDniException;
import com.prueba.prueba.model.Address;
import com.prueba.prueba.model.Customer;
import com.prueba.prueba.repositories.AddressRepository;
import com.prueba.prueba.repositories.CustomerRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Transactional
@Service
@AllArgsConstructor
public class CustomerService implements ICustomerService {

    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer create(Customer customer) throws DuplicatedDniException {
        // Comprobamos si existe un cliente con el mismo DNI
        Optional<Customer> existingCustomer = customerRepository.findByDniCustomer(customer.getDniCustomer());
        if (existingCustomer.isPresent()) {
            throw new DuplicatedDniException("A customer with the provided DNI already exists");
        }

        Customer savedCustomer = customerRepository.save(customer);

        // Crear y guardar las direcciones adicionales, si las hay
        if (customer.getAddresses() != null) {
            for (Address address : customer.getAddresses()) {
                address.setCustomer(savedCustomer);
                addressRepository.save(address);
            }
        }

        return savedCustomer;
    }

    @Override
    public void delete(Long id) {
        Optional<Customer> existingCustomer = customerRepository.findById(id);
        if (existingCustomer.isPresent()) {
            Customer customer = existingCustomer.get();

            // Eliminar las direcciones asociadas al cliente
            for (Address address : customer.getAddresses()) {
                addressRepository.delete(address);
            }

            // Finalmente eliminar el cliente
            customerRepository.delete(customer);
        } else {
            throw new EntityNotFoundException("Customer no encontrado id" + id);
        }
    }

    @Override
    public List<Customer> findByNameOrDni(String name, String dni) {
        return customerRepository.findByNameOrDniCustomer(name, dni);
    }

    @Override
    public Customer update(Customer customer) {
        Optional<Customer> existingCustomer = customerRepository.findById(customer.getId());
        if (existingCustomer.isPresent()) {
            // Verificar si hay otro cliente con el mismo DNI
            Optional<Customer> customerWithSameDni = findByDniCustomer(customer.getDniCustomer());
            if (customerWithSameDni.isPresent() && !customerWithSameDni.get().getId().equals(customer.getId())) {
                throw new DuplicateEntityException("Ya existe un cliente con el DNI: " + customer.getDniCustomer());
            }

            Customer dbCustomer = existingCustomer.get();
            dbCustomer.setName(customer.getName());
            dbCustomer.setDniCustomer(customer.getDniCustomer());

            return customerRepository.save(dbCustomer);
        } else {
            throw new EntityNotFoundException("Cliente no encontrado con el id " + customer.getId());
        }

    }

    @Override
    public Optional<Customer> findByDniCustomer(String dniCustomer) {

        return customerRepository.findByDniCustomer(dniCustomer);
    }
}
