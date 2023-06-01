package com.prueba.prueba.services;

import java.util.List;

import com.prueba.prueba.dto.AddressDto;
import com.prueba.prueba.model.Address;

public interface IAddressService {
    public Address get (Long id);
    public Address create(Address address);
    public List<Address> getAllAddresses();
    public Address update(Long id, Address address);
    void delete(Long id);
    public AddressDto toDto(Address address);
    public Address toEntity(AddressDto addressDto);
    
}
