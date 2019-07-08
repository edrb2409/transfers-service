package io.edrb.transferservice.transfer.repository;

import io.edrb.transferservice.transfer.model.Transfer;
import io.edrb.transferservice.transfer.model.TransferLog;
import io.edrb.transferservice.transfer.model.TransferState;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class TransferLogRepositoryTest {

    private TransferLogRepository transferLogRepository;

    @Before
    public void init() {
        transferLogRepository = new InMemoryTransferLogRepository();
    }

    @Test
    public void shouldSaveATransferLog() {
        Transfer transfer = new Transfer();
        transfer.setSourceCustomerId("sourceCustomer-01");
        transfer.setSourceAccountId("sourceAccount-01");
        transfer.setDestinationAccount("destinationAccount-01");
        transfer.setAmount(new BigDecimal(100));

        TransferLog transferLog = new TransferLog();
        transferLog.setTransfer(transfer);
        transferLog.setState(TransferState.SUCCESS);

        TransferLog transferLogSaved = transferLogRepository.save(transferLog);

        assertThat(transferLogSaved.getId(), is(notNullValue()));
    }
}
