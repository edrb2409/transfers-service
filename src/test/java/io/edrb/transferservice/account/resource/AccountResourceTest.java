package io.edrb.transferservice.account.resource;

import io.edrb.transferservice.account.config.AccountBinding;
import io.edrb.transferservice.account.model.Account;
import io.edrb.transferservice.customer.config.CustomerBinding;
import io.edrb.transferservice.customer.model.Customer;
import io.edrb.transferservice.customer.resource.CustomerResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AccountResourceTest extends JerseyTest {

    @Override
    protected Application configure() {
        ResourceConfig resourceConfig = new ResourceConfig(AccountResource.class, CustomerResource.class);
        resourceConfig.register(new AccountBinding());
        resourceConfig.register(new CustomerBinding());

        return resourceConfig;
    }

    @Test
    public void shouldSaveANewAccount() {
        Response saveCustomerResponse = saveOneCustomerRequest();

        assertThat(saveCustomerResponse.getStatus(), is(200));

        String customerId = saveCustomerResponse.readEntity(Customer.class).getId();

        Response saveAccountResponse = saveOneAccount(customerId);

        assertThat(saveAccountResponse.getStatus(), is(200));
    }

    private Response saveOneAccount(String customerId) {
       return target("/customers/" + customerId + "/accounts")
               .request()
               .post(Entity.entity(defaultAccount(customerId), MediaType.APPLICATION_JSON));
    }

    private Response saveOneCustomerRequest() {
        return target("/customers")
                .request()
                .post(Entity.entity(defaultCustomer(), MediaType.APPLICATION_JSON));
    }

    private Customer defaultCustomer() {
        Customer customer = new Customer();
        customer.setCompleteName("Daniel");

        return customer;
    }

    private Account defaultAccount(String customerId) {
        Account account = new Account();
        account.setCustomerId(customerId);
        account.setBalance(new BigDecimal(200));
        return account;
    }
}
