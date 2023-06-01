package com.prueba.prueba.dto;

import com.prueba.prueba.model.Address;

import lombok.Data;

@Data
public class AddressDto {
    private Long id;
    private String address;
    private Boolean isMain; 
    
    
}
