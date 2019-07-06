package io.edrb.transferservice.customer.service;

import io.edrb.transferservice.customer.model.Customer;

import java.util.List;

public interface CustomerService {

    Customer save(Customer customer);

    Customer update(String id, Customer customer);

    Customer delete(String id);

    Customer findById(String id);

    List<Customer> findAll();
}
