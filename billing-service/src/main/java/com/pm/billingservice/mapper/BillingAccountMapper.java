package com.pm.billingservice.mapper;

import com.pm.billingservice.dto.BillingAccountResponseDTO;
import com.pm.billingservice.model.BillingAccount;

import java.util.List;

public class BillingAccountMapper {

    public  static BillingAccountResponseDTO toDTO(BillingAccount billingAccount){
        return BillingAccountResponseDTO.builder()
                .accountId(billingAccount.getAccountId())
                .name(billingAccount.getName())
                .email(billingAccount.getEmail())
                .patientId(billingAccount.getPatientId())
                .doctorId(billingAccount.getDoctorId())
                .status(billingAccount.getStatus())
                .build();
    }
}
