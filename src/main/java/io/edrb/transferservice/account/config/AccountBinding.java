package io.edrb.transferservice.account.config;

import io.edrb.transferservice.account.repository.AccountRepository;
import io.edrb.transferservice.account.repository.InMemoryAccountRepository;
import io.edrb.transferservice.account.service.AccountService;
import io.edrb.transferservice.account.service.DefaultAccountService;
import org.glassfish.jersey.internal.inject.AbstractBinder;

import javax.inject.Singleton;

public class AccountBinding extends AbstractBinder {

    @Override
    protected void configure() {
        bind(InMemoryAccountRepository.class).to(AccountRepository.class).in(Singleton.class);
        bind(DefaultAccountService.class).to(AccountService.class);
    }
}
