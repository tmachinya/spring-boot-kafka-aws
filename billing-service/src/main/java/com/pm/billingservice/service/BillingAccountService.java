package com.pm.billingservice.service;

import com.pm.billingservice.model.BillingAccount;

import java.util.List;

public interface BillingAccountService {
    List<BillingAccount> getAllBillingAccounts();
}
