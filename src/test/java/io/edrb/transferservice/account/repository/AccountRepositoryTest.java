package io.edrb.transferservice.account.repository;

import io.edrb.transferservice.account.model.Account;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

public class AccountRepositoryTest {

    private AccountRepository accountRepository;

    @Before
    public void init() {
        accountRepository = new InMemoryAccountRepository();
    }

    @Test
    public void shouldSaveANewAccount() {
        Account account = new Account();
        account.setBalance(new BigDecimal(100));
        account.setCustomerId("1");

        accountRepository.save(account);

        assertThat(account.getId(), is(notNullValue()));
    }

    @Test
    public void shouldDeleteAnAccount() {
        Account account = new Account();
        account.setId("account-1");
        account.setBalance(new BigDecimal(100));
        account.setCustomerId("1");

        accountRepository.save(account);

        assertTrue(accountRepository.findById("account-1").isPresent());

        accountRepository.deleteById("account-1");

        assertFalse(accountRepository.findById("account-1").isPresent());
    }

}
