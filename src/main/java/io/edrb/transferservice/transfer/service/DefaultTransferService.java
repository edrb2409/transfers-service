package io.edrb.transferservice.transfer.service;

import io.edrb.transferservice.account.exception.AccountNotFoundException;
import io.edrb.transferservice.account.exception.FundsNotAvailableException;
import io.edrb.transferservice.account.model.Account;
import io.edrb.transferservice.account.service.AccountService;
import io.edrb.transferservice.customer.exception.CustomerNotFoundException;
import io.edrb.transferservice.customer.service.CustomerService;
import io.edrb.transferservice.transfer.model.Transfer;
import io.edrb.transferservice.transfer.model.TransferLog;
import io.edrb.transferservice.transfer.model.TransferState;
import io.edrb.transferservice.transfer.repository.TransferLogRepository;

import javax.inject.Inject;
import java.util.List;
import java.util.logging.Logger;

public class DefaultTransferService implements TransferService {

    private static final Logger LOGGER = Logger.getLogger("DefaultTransferService");

    @Inject
    private CustomerService customerService;

    @Inject
    private AccountService accountService;

    @Inject
    private TransferLogRepository transferLogRepository;

    @Override
    public void process(String sourceCustomerId, String sourceAccountId, Transfer transfer) {
        LOGGER.info("Processing a transfer " + transfer + " for " + sourceCustomerId + ", " + sourceAccountId);

        final TransferLog transferLog = new TransferLog();

        try {
            transfer.setSourceCustomerId(sourceCustomerId);
            transfer.setSourceAccountId(sourceAccountId);
            transferLog.setTransfer(transfer);

            customerService.findById(sourceCustomerId);

            final Account sourceAccount = accountService.findById(sourceAccountId);
            final Account destinationAccount = accountService.findById(transfer.getDestinationAccount());

            sourceAccount.withdrawal(transfer.getAmount());
            destinationAccount.deposit(transfer.getAmount());

            accountService.update(sourceAccount);
            accountService.update(destinationAccount);

            transferLog.setState(TransferState.SUCCESS);

        } catch (CustomerNotFoundException | AccountNotFoundException |
                FundsNotAvailableException e) {
            transferLog.setState(TransferState.FAILURE);
            throw e;
        } finally {
            LOGGER.info("Transaction Processed: " + transferLog);

            transferLogRepository.save(transferLog);
        }
    }

    @Override
    public List<TransferLog> findAll(String customerId, String accountId) {
        return transferLogRepository.findAllBy(customerId, accountId);
    }
}
