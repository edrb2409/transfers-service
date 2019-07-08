package io.edrb.transferservice.transfer.repository;


import io.edrb.transferservice.transfer.model.TransferLog;

import java.util.List;

public interface TransferLogRepository {

    TransferLog save(TransferLog entity);

    List<TransferLog> findAllBy(String customerId, String accountId);

}
