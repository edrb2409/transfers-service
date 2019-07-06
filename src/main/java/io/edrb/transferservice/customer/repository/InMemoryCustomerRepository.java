package io.edrb.transferservice.customer.repository;

import io.edrb.transferservice.customer.model.Customer;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class InMemoryCustomerRepository implements CustomerRepository {

    private final ConcurrentMap<String, Customer> customerRepository;

    public InMemoryCustomerRepository() {
        this.customerRepository = new ConcurrentHashMap<>();
    }

    @Override
    public Customer save(Customer customer) {
        if(customer.getId() == null)
            customer.setId(UUID.randomUUID().toString());

        customerRepository.put(customer.getId(), customer);

        return customer;
    }

    @Override
    public Optional<Customer> findById(String id) {
        return Optional.ofNullable(customerRepository.get(id));
    }

    @Override
    public Customer deleteById(String id) {
        return customerRepository.remove(id);
    }

    @Override
    public List<Customer> findAll() {
        return new LinkedList<>(customerRepository.values());
    }
}
