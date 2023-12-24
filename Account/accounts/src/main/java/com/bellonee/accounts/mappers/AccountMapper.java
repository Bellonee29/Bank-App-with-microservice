package com.bellonee.accounts.mappers;

import com.bellonee.accounts.dto.AccountDto;
import com.bellonee.accounts.dto.CustomerDto;
import com.bellonee.accounts.entity.Accounts;
import com.bellonee.accounts.entity.Customer;

public class AccountMapper {
    public AccountDto mapToAccountDto(Accounts accounts, AccountDto accountDto){
        accountDto.setAccountNumber(accounts.getAccountNumber());
        accountDto.setAccountType(accounts.getAccountType());
        accountDto.setBranchAddress(accounts.getBranchAddress());
        return accountDto;
    }

    public Accounts mapToCustomerDto(AccountDto accountDto,Accounts accounts ){
        accounts.setAccountNumber(accountDto.getAccountNumber());
        accounts.setAccountType(accountDto.getAccountType());
        accounts.setBranchAddress(accountDto.getBranchAddress());
        return accounts;
    }
}
