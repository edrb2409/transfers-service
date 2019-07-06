package io.edrb.transferservice.customer.config;

import io.edrb.transferservice.customer.repository.CustomerRepository;
import io.edrb.transferservice.customer.repository.InMemoryCustomerRepository;
import io.edrb.transferservice.customer.service.CustomerService;
import io.edrb.transferservice.customer.service.DefaultCustomerService;
import org.glassfish.jersey.internal.inject.AbstractBinder;

public class CustomerBinding extends AbstractBinder {

    @Override
    protected void configure() {
        bind(InMemoryCustomerRepository.class).to(CustomerRepository.class);
        bind(DefaultCustomerService.class).to(CustomerService.class);
    }
}
