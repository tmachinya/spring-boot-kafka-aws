package com.pm.billingservice.grpc;

import billing.BillingRequest;
import billing.BillingResponse;
import billing.BillingServiceGrpc.BillingServiceImplBase;
import com.pm.billingservice.model.BillingAccount;
import com.pm.billingservice.repository.BillingAccountRepository;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GrpcService
public class BillingGrpcService extends BillingServiceImplBase {

    private final BillingAccountRepository billingAccountRepository;

    private static final Logger log = LoggerFactory.getLogger(
            BillingGrpcService.class);

    public BillingGrpcService(BillingAccountRepository billingAccountRepository) {
        this.billingAccountRepository = billingAccountRepository;
    }

    @Override
    public void createBillingAccount(BillingRequest billingRequest,
                                     StreamObserver<BillingResponse> responseObserver) {

        log.info("createBillingAccount request received {}", billingRequest.toString());

        // Business logic - e.g save to database, perform calculates etc
        BillingAccount billing = billingAccountRepository
                .save(BillingAccount.builder()
                    .accountId("acc12345")
                    .email(billingRequest.getEmail())
                    .name(billingRequest.getName())
                    .patientId(billingRequest.getPatientId())
                    .doctorId("R1234546")
                    .status("ACTIVE")
                .build());

        BillingResponse response = BillingResponse.newBuilder()
                .setAccountId(billing.getAccountId())
                .setStatus(billing.getStatus())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}