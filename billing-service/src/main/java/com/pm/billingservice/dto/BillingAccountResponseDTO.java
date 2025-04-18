package com.pm.billingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BillingAccountResponseDTO {
    private String accountId;
    private String doctorId;
    private String patientId;
    private String status;
    private String name;
    private String email;

}
