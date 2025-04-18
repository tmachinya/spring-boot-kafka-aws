package com.pm.billingservice.serviceImpl;

import com.pm.billingservice.model.BillingAccount;
import com.pm.billingservice.repository.BillingAccountRepository;
import com.pm.billingservice.service.BillingAccountService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillingAccountServiceImpl implements BillingAccountService {
    private final BillingAccountRepository billingRepository;

    public BillingAccountServiceImpl(BillingAccountRepository billingAccountRepository) {
        this.billingRepository = billingAccountRepository;
    }

    @Override
    public List<BillingAccount> getAllBillingAccounts() {
        return billingRepository.findAll();
    }
}
