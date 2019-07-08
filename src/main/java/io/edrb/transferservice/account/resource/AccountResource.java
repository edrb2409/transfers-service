package io.edrb.transferservice.account.resource;

import io.edrb.transferservice.account.model.Account;
import io.edrb.transferservice.account.service.AccountService;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("customers/{customerId}/accounts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Singleton
public class AccountResource {

    @Inject
    private AccountService accountService;

    @GET
    public List<Account> getAllAccountsForUser(@PathParam("customerId") String customerId) {
        return accountService.findAllByCustomer(customerId);
    }

    @POST
    public Account saveAccount(@PathParam("customerId") String customerId,
                              Account account) {
        return accountService.save(customerId, account);
    }

    @GET
    @Path("/{accountId}")
    public Account getAccount(@PathParam("customerId") String customerId,
                            @PathParam("accountId") String accountId) {
        return accountService.findById(customerId, accountId);
    }


    @DELETE
    @Path("/{accountId}")
    public Account deleteAccount(@PathParam("customerId") String customerId,
                                 @PathParam("accountId") String accountId) {
        return accountService.delete(customerId, accountId);
    }

}
