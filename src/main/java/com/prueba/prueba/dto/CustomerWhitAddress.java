package com.prueba.prueba.dto;

import java.util.List;

import com.prueba.prueba.model.Address;
import com.prueba.prueba.model.Customer;


import lombok.Data;

@Data
public class CustomerWhitAddress extends CustomerDto{

  
    private Long id;

    private Address mainAddress;

    private Boolean isMain;

    private List<AddressDto> addresses;
    public Customer toCustomer(){
        Customer customer = new Customer();
        customer.setId(this.getId());
        customer.setName(this.getName());
        customer.setDniCustomer(this.getDniCustomer());
        return customer;
    } 

    public List<AddressDto> getAddresses() {
        return this.addresses;
    }
}
