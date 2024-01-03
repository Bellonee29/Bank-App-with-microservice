package com.bellonee.accounts.service;

import com.bellonee.accounts.dto.CustomerDto;

public interface IAccountService {
    /**
     *
     */

    void createAccount (CustomerDto customerDto);
    CustomerDto fetchAccount (String phoneNumber);
    boolean updateAccount (CustomerDto customerDto);
}
