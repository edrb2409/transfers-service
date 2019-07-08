package io.edrb.transferservice.account.service;

import io.edrb.transferservice.account.exception.AccountNotFoundException;
import io.edrb.transferservice.account.model.Account;
import io.edrb.transferservice.account.repository.AccountRepository;
import io.edrb.transferservice.customer.service.CustomerService;

import javax.inject.Inject;
import java.util.List;

public class DefaultAccountService implements AccountService {

    @Inject
    private AccountRepository accountRepository;

    @Inject
    private CustomerService customerService;

    @Override
    public Account save(String customerId, Account account) {
        customerService.findById(customerId);

        account.setCustomerId(customerId);

        return save(account);
    }

    @Override
    public List<Account> findAllByCustomer(String customerId) {
        return accountRepository.findAllBy(customerId);
    }

    @Override
    public Account delete(String customerId, String accountId) {
        customerService.findById(customerId);

        accountRepository.findById(accountId)
                .orElseThrow(AccountNotFoundException::new);

        return accountRepository.deleteById(accountId);
    }

    @Override
    public Account findById(String accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(AccountNotFoundException::new);
    }

    @Override
    public Account update(Account account) {
        Account accountFound = accountRepository.findById(account.getId())
                .orElseThrow(AccountNotFoundException::new);

        accountFound.setBalance(account.getBalance());

        return save(account);
    }

    @Override
    public Account findById(String customerId, String accountId) {
        customerService.findById(customerId);

        return findById(accountId);
    }

    private Account save(Account account) {
        return accountRepository.save(account);
    }
}
