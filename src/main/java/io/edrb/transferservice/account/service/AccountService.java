package io.edrb.transferservice.account.service;

import io.edrb.transferservice.account.model.Account;

import java.util.List;

public interface AccountService {

    Account save(String customerId, Account account);

    List<Account> findAllByCustomer(String customerId);

    Account delete(String customerId, String accountId);

    Account findById(String accountId);
}
