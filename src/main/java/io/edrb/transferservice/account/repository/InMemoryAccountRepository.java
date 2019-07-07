package io.edrb.transferservice.account.repository;

import io.edrb.transferservice.account.model.Account;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

public class InMemoryAccountRepository implements AccountRepository {

    private final ConcurrentMap<String, Account> accountRepository;

    public InMemoryAccountRepository() {
        this.accountRepository = new ConcurrentHashMap<>();
    }

    @Override
    public Account save(Account account) {
        if(account.getId() == null)
            account.setId(UUID.randomUUID().toString());

        accountRepository.put(account.getId(), account);

        return account;
    }

    @Override
    public Account deleteById(String id) {
        return accountRepository.remove(id);
    }

    @Override
    public Optional<Account> findById(String id) {
        return Optional.ofNullable(accountRepository.get(id));
    }

    @Override
    public List<Account> findAll() {
        return new LinkedList<>(accountRepository.values());
    }

    @Override
    public List<Account> findAllBy(String customerId) {
        return findAll().stream()
                .filter(it -> it.getCustomerId().equals(customerId))
                .collect(Collectors.toList());
    }
}
