package io.edrb.transferservice.customer.resource;


import io.edrb.transferservice.customer.config.CustomerBinding;
import io.edrb.transferservice.customer.model.Customer;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CustomerResourceTest extends JerseyTest {

    @Override
    protected Application configure() {
        ResourceConfig resourceConfig = new ResourceConfig(CustomerResource.class);
        resourceConfig.register(new CustomerBinding());

        return resourceConfig;
    }

    @Test
    public void shouldSaveANewCustomerWhenCustomerIsCorrect() {
        Response response = saveOneCustomerRequest();

        assertThat(response.getStatus(), is(200));
    }

    @Test
    public void shouldReturnAllCustomers() {
        Response saveOne = saveOneCustomerRequest();

        assertThat(saveOne.getStatus(), is(200));

        List allCustomers = getAllRequest();

        assertFalse(allCustomers.isEmpty());
        assertThat(((Map)allCustomers.get(0)).get("completeName"), is("Daniel"));
    }


    @Test
    public void shouldDeleteACustomerById() {
        Response saveOne = saveOneCustomerRequest();

        assertThat(saveOne.getStatus(), is(200));

        String customerId = saveOne.readEntity(Customer.class).getId();

        Response deleteResponse = deleteRequest(customerId);

        assertThat(deleteResponse.getStatus(), is(200
        ));
    }

    private Response saveOneCustomerRequest() {
        return target("/customers")
                .request()
                .post(Entity.entity(defaultCustomer(), MediaType.APPLICATION_JSON));
    }

    private List getAllRequest() {
        return target("/customers")
                .request()
                .get(List.class);
    }

    private Response deleteRequest(String customerId) {
        return target("/customers/" + customerId)
                .request()
                .delete();
    }

    private Customer defaultCustomer() {
        Customer customer = new Customer();
        customer.setCompleteName("Daniel");

        return customer;
    }
}
