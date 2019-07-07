package io.edrb.transferservice.account.service;

import io.edrb.transferservice.account.model.Account;
import io.edrb.transferservice.account.repository.AccountRepository;
import io.edrb.transferservice.customer.model.Customer;
import io.edrb.transferservice.customer.service.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

    @Mock
    private CustomerService customerService;

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService = new DefaultAccountService();

    @Test
    public void shouldSaveAnAccount() {
        Account account = new Account();
        account.setBalance(new BigDecimal(200));

        when(customerService.findById("customer01"))
                .thenReturn(new Customer());

        when(accountRepository.save(account))
                .thenReturn(expectedAccount());

        Account accountSaved = accountService.save("customer01", account);

        assertThat(accountSaved.getId(), is(notNullValue()));
    }

    private Account expectedAccount() {
        Account account = new Account();
        account.setId("account-1");
        account.setCustomerId("customer01");
        account.setBalance(new BigDecimal(200));

        return account;
    }
}
