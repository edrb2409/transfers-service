package io.edrb.transferservice.customer.repository;

import io.edrb.transferservice.customer.model.Customer;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

public class CustomerRepositoryTest {

    private CustomerRepository customerRepository;

    @Before
    public void init() {
        customerRepository = new InMemoryCustomerRepository();
    }

    @Test
    public void shouldSaveANewCustomer() {
        Customer customer = new Customer();
        customer.setCompleteName("Daniel Ron");

        customerRepository.save(customer);

        assertThat(customer.getId(), is(notNullValue()));
    }

    @Test
    public void shouldUpdateACustomer() {
        Customer customer = new Customer();
        customer.setCompleteName("Daniel Ron");
        customer.setId("customer01");

        customerRepository.save(customer);

        assertTrue(customerRepository.findById("customer01").isPresent());
        assertThat(customerRepository.findById("customer01").get().getCompleteName(), is("Daniel Ron"));

        customer.setCompleteName("Edwin Bastidas");

        customerRepository.save(customer);

        assertTrue(customerRepository.findById("customer01").isPresent());
        assertThat(customerRepository.findById("customer01").get().getCompleteName(), is("Edwin Bastidas"));
    }

    @Test
    public void shouldFindACustomerById() {
        Customer customer = new Customer();
        customer.setCompleteName("Daniel Ron");
        customer.setId("customer01");

        customerRepository.save(customer);

        assertTrue(customerRepository.findById("customer01").isPresent());
        assertThat(customerRepository.findById("customer01").get().getCompleteName(), is("Daniel Ron"));
    }

    @Test
    public void shouldNotFindACustomerWhenCustomerNotExists() {
        assertFalse(customerRepository.findById("customer01").isPresent());
    }

    @Test
    public void shouldDeleteACustomerById() {
        Customer customer = new Customer();
        customer.setCompleteName("Daniel Ron");
        customer.setId("customer01");

        customerRepository.save(customer);

        assertTrue(customerRepository.findById("customer01").isPresent());
        assertThat(customerRepository.findById("customer01").get().getCompleteName(), is("Daniel Ron"));

        customerRepository.deleteById("customer01");

        assertFalse(customerRepository.findById("customer01").isPresent());
    }

    @Test
    public void shouldReturnAListOfCustomersFound() {
        Customer customer = new Customer();
        customer.setCompleteName("Daniel Ron");
        customer.setId("customer01");

        customerRepository.save(customer);

        assertThat(customerRepository.findAll().size(), is(1));
    }
}
