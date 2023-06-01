package com.prueba.prueba.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.prueba.dto.AddressDto;
import com.prueba.prueba.dto.CustomerWhitAddress;
import com.prueba.prueba.model.Address;
import com.prueba.prueba.model.Customer;

import com.prueba.prueba.services.IAddressService;
import com.prueba.prueba.services.ICustomerService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/customer")
public class CustomerController {
    private final ICustomerService customerService;
    private final IAddressService addressService;
    

    @PostMapping
    public ResponseEntity <Object> create(@Valid @RequestBody CustomerWhitAddress customerDto) {
    // Crear y guardar el cliente
    Customer customer = customerDto.toCustomer();
    Customer savedCustomer = customerService.create(customer);
    
    // Crear y guardar la dirección principal
    Address mainAddress = customerDto.getMainAddress();
    mainAddress.setCustomer(savedCustomer);
    mainAddress.setIsMain(true);
    addressService.create(mainAddress);
    
    // Asignar la dirección principal al cliente
    savedCustomer.setMainAddress(mainAddress);
    
    // Crear y guardar las direcciones adicionales, si las hay
    List<Address> addresses = new ArrayList<>();
    if (customerDto.getMainAddress() != null) {
        for (AddressDto addressDto : customerDto.getAddresses()) {
            Address address = new Address();
            address.setAddress(addressDto.getAddress());
            address.setIsMain(false);
            address.setCustomer(savedCustomer);
            Address savedAddress = addressService.create(address);
            addresses.add(savedAddress);
        }
}
    
    // Asignar las direcciones adicionales al cliente
    savedCustomer.setAddresses(addresses);
    
    // Actualizar el cliente en la base de datos
    customerService.update(savedCustomer); // Aquí guardamos el cliente actualizado
    
    return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomer);
       
    }
    @GetMapping("/search")
public ResponseEntity<List<Customer>> findByNameOrDni(@RequestParam String name, @RequestParam String dni) {
    return ResponseEntity.ok(customerService.findByNameOrDni(name, dni));
}

@PutMapping("/{id}")
public ResponseEntity<Address> update(@PathVariable Long id, @RequestBody Address address) {
    return ResponseEntity.ok(addressService.update(id, address));
}
@DeleteMapping("/{id}")
public ResponseEntity<Void> delete(@PathVariable Long id) {
    addressService.delete(id);
    return ResponseEntity.noContent().build();
}
    
}
