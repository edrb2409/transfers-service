package io.edrb.transferservice.config;

import io.edrb.transferservice.account.config.AccountBinding;
import io.edrb.transferservice.customer.config.CustomerBinding;
import io.edrb.transferservice.transfer.config.TransferBinding;
import org.glassfish.jersey.server.ResourceConfig;

public class TransferServiceConfig extends ResourceConfig {

    public TransferServiceConfig() {
        register(new CustomerBinding());
        register(new AccountBinding());
        register(new TransferBinding());
        packages(true, "io.edrb.transferservice");
    }
}
