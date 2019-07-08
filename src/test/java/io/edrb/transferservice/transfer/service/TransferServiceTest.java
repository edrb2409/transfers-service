package io.edrb.transferservice.transfer.service;

import io.edrb.transferservice.account.exception.AccountNotFoundException;
import io.edrb.transferservice.account.exception.FundsNotAvailableException;
import io.edrb.transferservice.account.model.Account;
import io.edrb.transferservice.account.service.AccountService;
import io.edrb.transferservice.customer.exception.CustomerNotFoundException;
import io.edrb.transferservice.customer.model.Customer;
import io.edrb.transferservice.customer.service.CustomerService;
import io.edrb.transferservice.transfer.model.Transfer;
import io.edrb.transferservice.transfer.repository.TransferLogRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TransferServiceTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Mock
    private CustomerService customerService;

    @Mock
    private AccountService accountService;

    @Mock
    private TransferLogRepository transferLogRepository;

    @InjectMocks
    private TransferService transferService = new DefaultTransferService();

    @Test
    public void shouldRaiseAnExceptionWhenCustomerNotFound() {
        thrown.expect(CustomerNotFoundException.class);

        when(customerService.findById("customer-01"))
                .thenThrow(CustomerNotFoundException.class);

        transferService.process("customer-01", "sourceAccount-01", transfer());
    }

    @Test
    public void shouldRaiseAnExceptionWhenSourceAccountNotFound() {
        thrown.expect(AccountNotFoundException.class);

        when(customerService.findById("customer-01"))
                .thenReturn(customer());

        when(accountService.findById("sourceAccount-01"))
                .thenThrow(AccountNotFoundException.class);

        transferService.process("customer-01", "sourceAccount-01", transfer());
    }

    @Test
    public void shouldRaiseAnExceptionWhenDestinationAccountNotFound() {
        thrown.expect(AccountNotFoundException.class);

        when(customerService.findById("customer-01"))
                .thenReturn(customer());

        when(accountService.findById("sourceAccount-01"))
                .thenReturn(sourceAccount(new BigDecimal(100)));

        when(accountService.findById("destinationAccount-01"))
                .thenThrow(AccountNotFoundException.class);

        transferService.process("customer-01", "sourceAccount-01", transfer());
    }

    @Test
    public void shouldRaiseAnExceptionWhenSourceAccountDoesNotHaveFunds() {
        thrown.expect(FundsNotAvailableException.class);

        when(customerService.findById("customer-01"))
                .thenReturn(customer());

        when(accountService.findById("sourceAccount-01"))
                .thenReturn(sourceAccount(new BigDecimal(50)));

        when(accountService.findById("destinationAccount-01"))
                .thenReturn(destinationAccount());

        transferService.process("customer-01", "sourceAccount-01", transfer());
    }

    @Test
    public void shouldProcessATransferBetweenKnownAccounts() {
        when(customerService.findById("customer-01"))
                .thenReturn(customer());

        when(accountService.findById("sourceAccount-01"))
                .thenReturn(sourceAccount(new BigDecimal(100)));

        when(accountService.findById("destinationAccount-01"))
                .thenReturn(destinationAccount());

        transferService.process("customer-01", "sourceAccount-01", transfer());
    }

    private Transfer transfer() {
        Transfer transfer = new Transfer();
        transfer.setDestinationAccount("destinationAccount-01");
        transfer.setAmount(new BigDecimal(100));

        return transfer;
    }

    private Customer customer() {
        Customer customer = new Customer();
        customer.setCompleteName("Daniel");
        customer.setId("customer-01");

        return customer;
    }

    private Account sourceAccount(BigDecimal balance) {
        Account account = new Account();
        account.setId("sourceAccount-01");
        account.setBalance(balance);
        account.setCustomerId("customer-01");

        return account;
    }

    private Account destinationAccount() {
        Account account = new Account();
        account.setId("destinationAccount-01");
        account.setBalance(new BigDecimal(150));
        account.setCustomerId("customer-02");

        return account;
    }
}
