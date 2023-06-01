package com.prueba.prueba.dto;

import com.prueba.prueba.model.Customer;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CustomerWhitAddress extends CustomerDto{

  
    private Long id;

    private String address;

    private Boolean isMain;

    public Customer toCustomer(){
        Customer customer = new Customer();
        customer.setId(this.getId());
        customer.setName(this.getName());
        customer.setDniCustomer(this.getDniCustomer());
        return customer;
    } 
}
