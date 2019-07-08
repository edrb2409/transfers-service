package io.edrb.transferservice.customer.resource;

import io.edrb.transferservice.customer.model.Customer;
import io.edrb.transferservice.customer.service.CustomerService;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.logging.Logger;

@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Singleton
public class CustomerResource {

    private static final Logger LOGGER = Logger.getLogger("CustomerResource");

    @Inject
    private CustomerService customerService;

    @GET
    public List<Customer> getAllCustomers() {
        return customerService.findAll();
    }

    @POST
    public Customer saveCustomer(Customer customer) {
        LOGGER.info("Saving customer " + customer);
        return customerService.save(customer);
    }

    @DELETE
    @Path("/{id}")
    public Customer deleteCustomer(@PathParam("id") String id) {
        return customerService.delete(id);
    }

}
