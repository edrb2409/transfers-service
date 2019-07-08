package io.edrb.transferservice.transfer.config;

import io.edrb.transferservice.transfer.repository.InMemoryTransferLogRepository;
import io.edrb.transferservice.transfer.repository.TransferLogRepository;
import io.edrb.transferservice.transfer.service.DefaultTransferService;
import io.edrb.transferservice.transfer.service.TransferService;
import org.glassfish.jersey.internal.inject.AbstractBinder;

import javax.inject.Singleton;

public class TransferBinding extends AbstractBinder {

    @Override
    protected void configure() {
        bind(InMemoryTransferLogRepository.class).to(TransferLogRepository.class).in(Singleton.class);
        bind(DefaultTransferService.class).to(TransferService.class);
    }
}
