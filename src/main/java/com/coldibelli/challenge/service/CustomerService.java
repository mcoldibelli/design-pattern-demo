package com.coldibelli.challenge.service;

import com.coldibelli.challenge.model.Customer;

public interface CustomerService {

    Iterable<Customer> findAll();

    Customer findById(Long id);

    void insert(Customer customer);

    void update(Long id, Customer customer);

    void delete(Long id);
}
