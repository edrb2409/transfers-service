package io.edrb.transferservice.transfer.resource;

import io.edrb.transferservice.transfer.model.Transfer;
import io.edrb.transferservice.transfer.model.TransferLog;
import io.edrb.transferservice.transfer.service.TransferService;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("customers/{customerId}/accounts/{accountId}/transfers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Singleton
public class TransferResource {

    @Inject
    private TransferService transferService;

    @POST
    public Response transferMoney(@PathParam("customerId") String customerId,
                                  @PathParam("accountId") String accountId,
                                  Transfer transfer) {

        transferService.process(customerId, accountId, transfer);

        return Response.ok().build();
    }

    @GET
    public List<TransferLog> getAllTransfers(@PathParam("customerId") String customerId,
                                             @PathParam("accountId") String accountId) {

        return transferService.findAll(customerId, accountId);
    }
}
