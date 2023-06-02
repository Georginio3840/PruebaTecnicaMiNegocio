package com.prueba.prueba.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.prueba.prueba.dto.AddressDto;
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
public class AddressService implements IAddressService {

    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;

    @Override
    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    @Override
    public Address get(Long id) {

        return addressRepository.findById(id).orElse(null);
    }

    @Override
    public Address create(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public Address update(Long id, Address address) {
        return addressRepository.findById(id)
                .map(existingAddress -> {
                    existingAddress.setAddress(address.getAddress());
                    existingAddress.setIsMain(address.getIsMain());
                    return addressRepository.save(existingAddress);
                })
                .orElseThrow(() -> new EntityNotFoundException("Address not found with id " + id));

    }

    @Override
    public void delete(Long id) {
        addressRepository.deleteById(id);
    }

    @Override
    public List<Address> getAddressesByCustomerId(Long id) {

        return addressRepository.findByCustomerId(id);

    }

    @Override
    public Address addAddressToCustomer(Long id, AddressDto addressDto) {
        Optional<Customer> customer = customerRepository.findById(id);

    if (!customer.isPresent()) {
        throw new EntityNotFoundException("Customer not found with id " + id);
    }

    Address address = new Address();
    address.setAddress(addressDto.getAddress());
    address.setIsMain(false);
    address.setCustomer(customer.get());

    return addressRepository.save(address);
}

}
