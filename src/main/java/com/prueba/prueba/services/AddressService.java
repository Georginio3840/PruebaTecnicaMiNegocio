package com.prueba.prueba.services;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.prueba.prueba.dto.AddressDto;
import com.prueba.prueba.model.Address;
import com.prueba.prueba.repositories.AddressRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;


@Transactional
@Service
@AllArgsConstructor
public class AddressService implements IAddressService {
   
    private final AddressRepository addressRepository;



    
    @Override
    public List<Address> getAllAddresses() {
        return  addressRepository.findAll();
      }




    @Override
    public Address get(Long id) {
        
        return addressRepository.findById(id).orElse(null);
    }




    @Override
    public Address create(Address address) {
        return addressRepository.save(address);
    }

    public AddressDto toDto(Address address) {
    AddressDto dto = new AddressDto();
    dto.setId(address.getId());
    dto.setAddress(address.getAddress());
    dto.setIsMain(address.getIsMain());
    // ... asignar otros campos
    return dto;
}

public Address toEntity(AddressDto dto) {
    Address address = new Address();
    address.setId(dto.getId());
    address.setAddress(dto.getAddress());
    address.setIsMain(dto.getIsMain());
    // ... asignar otros campos
    return address;
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

    
    
}
