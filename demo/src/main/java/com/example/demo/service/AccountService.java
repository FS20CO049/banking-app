package com.example.demo.service;

import com.example.demo.dto.AccountDto;

import java.util.List;

public interface AccountService {
    AccountDto createAccount(AccountDto accountDto);
    AccountDto getAccountById(Long id);
    AccountDto deposit(Long id,double balance);
    AccountDto withdraw(Long id,double balance);
    List<AccountDto> getAllaccounts();
    AccountDto deleteAccount(Long id);
}
