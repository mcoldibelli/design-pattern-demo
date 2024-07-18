package com.coldibelli.challenge.service;

import com.coldibelli.challenge.model.Address;
import com.coldibelli.challenge.model.Customer;
import com.coldibelli.challenge.repository.AddressRepository;
import com.coldibelli.challenge.repository.CustomerRepository;
import com.coldibelli.challenge.util.AddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ViaCepService viaCepService;

    @Override
    public Iterable<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.orElse(null);
    }

    @Override
    public void insert(Customer customer) {
        saveCustomerWithCep(customer);
    }

    @Override
    public void update(Long id, Customer customer) {
        Optional<Customer> customerDb = customerRepository.findById(id);
        if (customerDb.isPresent()) {
            saveCustomerWithCep(customer);
        }
    }

    @Override
    public void delete(Long id) {
        Optional<Customer> customerDb = customerRepository.findById(id);
        customerDb.ifPresent(customer -> customerRepository.delete(customer));
    }

    private void saveCustomerWithCep(Customer customer) {
        String cep = customer.getAddress().getCep();
        Address address = addressRepository.findById(cep).orElseGet(()-> {
            Address newAddress = AddressMapper.convertToAddress(viaCepService.findCep(cep));
            addressRepository.save(newAddress);
            return newAddress;
        });
        customer.setAddress(address);
        customerRepository.save(customer);
    }
}
