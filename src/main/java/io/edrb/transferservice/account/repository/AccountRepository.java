package io.edrb.transferservice.account.repository;

import io.edrb.transferservice.account.model.Account;
import io.edrb.transferservice.base.Repository;

import java.util.List;

public interface AccountRepository extends Repository<Account> {

    List<Account> findAllBy(String customerId);

}
