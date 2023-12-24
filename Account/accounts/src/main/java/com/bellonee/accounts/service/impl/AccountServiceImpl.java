package com.bellonee.accounts.service.impl;

import com.bellonee.accounts.dto.CustomerDto;
import com.bellonee.accounts.entity.Accounts;
import com.bellonee.accounts.entity.Customer;
import com.bellonee.accounts.mappers.CustomerMapper;
import com.bellonee.accounts.repository.AccountRepository;
import com.bellonee.accounts.repository.CustomerRepository;
import com.bellonee.accounts.service.IAccountService;
import com.bellonee.accounts.utils.ConstantAccount;

import java.util.Random;

public class AccountServiceImpl implements IAccountService {

    /**
     *
     */
    private CustomerRepository customerRepository;
    private AccountRepository accountRepository;
    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomerDto(customerDto, new Customer());
        Customer saveCustomer = customerRepository.save(customer);
        accountRepository.save(createNewAccount(saveCustomer));

    }

    private Accounts createNewAccount(Customer customer){
        Accounts newAccounts = new Accounts();
        newAccounts.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccounts.setAccountNumber(randomAccNumber);
        newAccounts.setAccountType(ConstantAccount.SAVINGs);
        newAccounts.setBranchAddress(ConstantAccount.ADDRESS);
        return newAccounts;
    }

}
