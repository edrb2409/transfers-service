package io.edrb.transferservice.transfer.resource;

import io.edrb.transferservice.account.config.AccountBinding;
import io.edrb.transferservice.account.model.Account;
import io.edrb.transferservice.account.resource.AccountResource;
import io.edrb.transferservice.customer.config.CustomerBinding;
import io.edrb.transferservice.customer.model.Customer;
import io.edrb.transferservice.customer.resource.CustomerResource;
import io.edrb.transferservice.transfer.config.TransferBinding;
import io.edrb.transferservice.transfer.model.Transfer;
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

public class TransferResourceTest extends JerseyTest {

    @Override
    protected Application configure() {
        ResourceConfig resourceConfig = new ResourceConfig(TransferResource.class, AccountResource.class, CustomerResource.class);
        resourceConfig.register(new AccountBinding());
        resourceConfig.register(new CustomerBinding());
        resourceConfig.register(new TransferBinding());

        return resourceConfig;
    }

    @Test
    public void shouldProcessAnTransfer() {
        Response customerDaniel = saveOneCustomerRequest("Daniel");
        Response customerEdwin = saveOneCustomerRequest("Edwin");

        assertThat(customerDaniel.getStatus(), is(200));
        assertThat(customerEdwin.getStatus(), is(200));

        final String customerDanielId = customerDaniel.readEntity(Customer.class).getId();
        final String customerEdwinId = customerEdwin.readEntity(Customer.class).getId();

        Response danielAccount = saveOneAccount(customerDanielId, new BigDecimal(100));
        Response edwinAccount = saveOneAccount(customerEdwinId, new BigDecimal(150));

        assertThat(danielAccount.getStatus(), is(200));
        assertThat(edwinAccount.getStatus(), is(200));

        final String danielAccountId = danielAccount.readEntity(Account.class).getId();
        final String edwinAccountId = edwinAccount.readEntity(Account.class).getId();

        Response transferResponse = makeATransfer(customerDanielId, danielAccountId,
                transfer(edwinAccountId, new BigDecimal(50)));

        assertThat(transferResponse.getStatus(), is(200));

        Response getDanielAccount = getOneAccount(customerDanielId, danielAccountId);
        Response getEdwinAccount = getOneAccount(customerEdwinId, edwinAccountId);

        assertThat(getDanielAccount.getStatus(), is(200));
        assertThat(getEdwinAccount.getStatus(), is(200));

        Account danielAccountObj = getDanielAccount.readEntity(Account.class);
        Account edwinAccountObj = getEdwinAccount.readEntity(Account.class);

        assertThat(danielAccountObj.getBalance(), is(new BigDecimal(50)));
        assertThat(edwinAccountObj.getBalance(), is(new BigDecimal(200)));
    }

    private Response saveOneCustomerRequest(String name) {
        return target("/customers")
                .request()
                .post(Entity.entity(defaultCustomer(name), MediaType.APPLICATION_JSON));
    }

    private Response saveOneAccount(String customerId, BigDecimal balance) {
        return target("/customers/" + customerId + "/accounts")
                .request()
                .post(Entity.entity(defaultAccount(customerId, balance), MediaType.APPLICATION_JSON));
    }

    private Response makeATransfer(String sourceCustomer, String sourceAccount, Transfer transfer) {
        return target("/customers/" + sourceCustomer + "/accounts/" + sourceAccount + "/transfers")
                .request()
                .post(Entity.entity(transfer, MediaType.APPLICATION_JSON));
    }

    private Response getOneAccount(String customerId, String accountId) {
        return target("/customers/" + customerId + "/accounts/" + accountId)
                .request()
                .get();
    }

    private Customer defaultCustomer(String name) {
        Customer customer = new Customer();
        customer.setCompleteName(name);

        return customer;
    }

    private Account defaultAccount(String customerId, BigDecimal balance) {
        Account account = new Account();
        account.setCustomerId(customerId);
        account.setBalance(balance);
        return account;
    }

    private Transfer transfer(String destinationAccount, BigDecimal amount) {
        Transfer transfer = new Transfer();
        transfer.setAmount(amount);
        transfer.setDestinationAccount(destinationAccount);

        return transfer;
    }
}
