package io.edrb.transferservice.customer.service;

import io.edrb.transferservice.customer.model.Customer;
import io.edrb.transferservice.customer.repository.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService = new DefaultCustomerService();

    @Test
    public void shouldSaveACustomer() {
        Customer customer = new Customer();
        customer.setCompleteName("Daniel");

        when(customerRepository.save(customer)).thenReturn(expectedCustomer());

        Customer customerSaved = customerService.save(customer);

        assertThat(customerSaved.getId(), is(notNullValue()));
    }

    private Customer expectedCustomer() {
        Customer customer = new Customer();
        customer.setCompleteName("Daniel");
        customer.setId(UUID.randomUUID().toString());
        return customer;
    }

}
