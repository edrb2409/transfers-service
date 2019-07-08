package io.edrb.transferservice.account.model;

import io.edrb.transferservice.account.exception.FundsNotAvailableException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AccountTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private Account account;

    @Before
    public void init() {
        account = new Account();
        account.setBalance(new BigDecimal(100));
    }

    @Test
    public void shouldRaiseAnExceptionWhenAccountDoesNotHaveBalanceForAWithdrawal() {
        thrown.expect(FundsNotAvailableException.class);

        account.withdrawal(new BigDecimal(200));
    }

    @Test
    public void shouldProcessTheWithdrawalWhenAccountHasEnoughBalance() {
        account.withdrawal(new BigDecimal(80));

        assertThat(account.getBalance(), is(new BigDecimal(20)));
    }

    @Test
    public void shouldIncreaseTheBalanceWhenAccountReceivesADeposit() {
        account.deposit(new BigDecimal(100));

        assertThat(account.getBalance(), is(new BigDecimal(200)));
    }

}
