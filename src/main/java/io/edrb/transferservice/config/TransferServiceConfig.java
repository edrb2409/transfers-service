package io.edrb.transferservice.config;

import org.glassfish.jersey.server.ResourceConfig;

public class TransferServiceConfig extends ResourceConfig {

    public TransferServiceConfig() {
        packages(true, "io.edrb.transferservice.resource");
    }
}
