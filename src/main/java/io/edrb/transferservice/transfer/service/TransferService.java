package io.edrb.transferservice.transfer.service;

import io.edrb.transferservice.transfer.model.Transfer;
import io.edrb.transferservice.transfer.model.TransferLog;

import java.util.List;

public interface TransferService {

    void process(String sourceCustomer, String sourceAccount, Transfer transfer);

    List<TransferLog> findAll(String customerId, String accountId);

}
