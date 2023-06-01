package com.prueba.prueba.controllers;

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
    Customer savedCustomer = customerService.create(customerDto.toCustomer());
    // Crear un nuevo objeto Address y asignar los datos
    Address address = new Address();
    address.setAddress(customerDto.getAddress());
    address.setIsMain(customerDto.getIsMain());
    address.setCustomer(savedCustomer);
    // Guardar la nueva Address en la base de datos
    addressService.create(address);
    // Asignar la nueva Address al Customer
    savedCustomer.setAddress(address);
    // Actualizar el Customer en la base de datos
    customerService.create(savedCustomer); // Aquí guardamos el Customer actualizado
    
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
