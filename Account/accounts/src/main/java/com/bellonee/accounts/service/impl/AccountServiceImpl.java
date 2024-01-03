package com.bellonee.accounts.service.impl;

import com.bellonee.accounts.dto.AccountDto;
import com.bellonee.accounts.dto.CustomerDto;
import com.bellonee.accounts.entity.Accounts;
import com.bellonee.accounts.entity.Customer;
import com.bellonee.accounts.exception.CustomerAlreadyExistException;
import com.bellonee.accounts.exception.ResourcesNotFoundException;
import com.bellonee.accounts.mappers.AccountMapper;
import com.bellonee.accounts.mappers.CustomerMapper;
import com.bellonee.accounts.repository.AccountRepository;
import com.bellonee.accounts.repository.CustomerRepository;
import com.bellonee.accounts.service.IAccountService;
import com.bellonee.accounts.utils.ConstantAccount;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;


@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService {

    /**
     *
     */
    private CustomerRepository customerRepository;
    private AccountRepository accountRepository;
    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomerDto(customerDto, new Customer());
        Optional<Customer> optionalCustomer = customerRepository.findByPhoneNumber(customerDto.getPhoneNumber());
        if (optionalCustomer.isPresent()){
            throw new CustomerAlreadyExistException("Customer Already exist with the provided number" + " " + customerDto.getPhoneNumber());
        }

        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("Bellonee");
        Customer saveCustomer = customerRepository.save(customer);
        accountRepository.save(createNewAccount(saveCustomer));

    }

    @Override
    public CustomerDto fetchAccount(String phoneNumber) {
      Customer customer =   customerRepository.findByPhoneNumber(phoneNumber).orElseThrow
                ( () -> new ResourcesNotFoundException("Customer", "PhoneNumber", phoneNumber)) ;

      Accounts accounts = accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
              () -> new ResourcesNotFoundException("Account", "customerId", customer.getCustomerId().toString())
      );
      CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
      customerDto.setAccountDto(AccountMapper.mapToAccountDto(accounts, new AccountDto()));
      return customerDto;

    }

    private Accounts createNewAccount(Customer customer){
        Accounts newAccounts = new Accounts();
        newAccounts.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccounts.setAccountNumber(randomAccNumber);
        newAccounts.setAccountType(ConstantAccount.SAVINGs);
        newAccounts.setBranchAddress(ConstantAccount.ADDRESS);
        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("Bellonee");
        return newAccounts;
    }

}
