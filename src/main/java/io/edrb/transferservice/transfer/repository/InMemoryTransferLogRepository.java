package io.edrb.transferservice.transfer.repository;

import io.edrb.transferservice.transfer.model.TransferLog;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

public class InMemoryTransferLogRepository implements TransferLogRepository {

    private final ConcurrentMap<String, TransferLog> transferLogConcurrentMap;

    public InMemoryTransferLogRepository() {
        this.transferLogConcurrentMap = new ConcurrentHashMap<>();
    }

    @Override
    public TransferLog save(TransferLog entity) {
        entity.setId(UUID.randomUUID().toString());

        transferLogConcurrentMap.put(entity.getId(), entity);

        return entity;
    }

    @Override
    public List<TransferLog> findAllBy(String customerId, String accountId) {
        return transferLogConcurrentMap.values().stream()
                .filter(it -> it.getTransfer().getSourceCustomerId().equals(customerId) &&
                        it.getTransfer().getSourceAccountId().equals(accountId))
                .collect(Collectors.toList());
    }
}
