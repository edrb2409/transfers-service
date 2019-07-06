package io.edrb.transferservice.customer.service;

import io.edrb.transferservice.customer.exception.CustomerNotFoundException;
import io.edrb.transferservice.customer.model.Customer;
import io.edrb.transferservice.customer.repository.CustomerRepository;

import javax.inject.Inject;
import java.util.List;

public class DefaultCustomerService implements CustomerService {

    @Inject
    private CustomerRepository customerRepository;

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer update(String id, Customer customer) {
        findById(id);

        customer.setId(id);

        return save(customer);
    }

    @Override
    public Customer delete(String id) {
        customerRepository.findById(id)
                .orElseThrow(CustomerNotFoundException::new);

        return customerRepository.deleteById(id);
    }

    @Override
    public Customer findById(String id) {
        return customerRepository.findById(id)
                .orElseThrow(CustomerNotFoundException::new);
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }
}
