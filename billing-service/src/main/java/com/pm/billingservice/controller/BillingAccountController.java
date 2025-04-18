package com.pm.billingservice.controller;

import com.pm.billingservice.dto.BillingAccountResponseDTO;
import com.pm.billingservice.mapper.BillingAccountMapper;
import com.pm.billingservice.service.BillingAccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/billing-accounts")
public class BillingAccountController {
    private final BillingAccountService billingAccountService;

    public BillingAccountController(BillingAccountService billingAccountService) {
        this.billingAccountService = billingAccountService;
    }

    @GetMapping
    public List<BillingAccountResponseDTO> getBillingAccounts() {
        return billingAccountService.getAllBillingAccounts()
                .stream().map(BillingAccountMapper::toDTO).toList();
    }
}
