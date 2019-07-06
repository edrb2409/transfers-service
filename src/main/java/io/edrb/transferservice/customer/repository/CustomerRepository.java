package io.edrb.transferservice.customer.repository;

import io.edrb.transferservice.customer.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {

    Customer save(Customer customer);

    Customer deleteById(String id);

    Optional<Customer> findById(String id);

    List<Customer> findAll();
}
