package com.prueba.prueba.dto;


import com.prueba.prueba.model.Address;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CustomerDto {

    
    private String name;
    private String dniCustomer;

    @NotNull(message = "El campo mainAddress no puede ser nulo")
    private Address mainAddress;

    
    
    
}
