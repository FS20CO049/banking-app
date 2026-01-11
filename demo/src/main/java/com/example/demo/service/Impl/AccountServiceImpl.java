package com.example.demo.service.Impl;

import com.example.demo.dto.AccountDto;
import com.example.demo.entity.Account;
import com.example.demo.mapper.AccountMapper;
import com.example.demo.repository.AccountRepository;
import com.example.demo.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;


    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto){
        Account account = AccountMapper.mapToAccount(accountDto);
        Account saveaccount = accountRepository.save(account);
        return  AccountMapper.mapToAccountDto(saveaccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account does not exist"));
        return AccountMapper.mapToAccountDto(account);

    }

    @Override
    public AccountDto deposit(Long id, double balance) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account does not exist"));
        double total = account.getBalance() + balance;
        account.setBalance(total);
        Account saveaccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(saveaccount);
    }

    @Override
    public AccountDto withdraw(Long id, double balance) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account does not exist"));
        if(account.getBalance() < balance){
            throw new RuntimeException("Insufficient Balance");
        }
        double total = account.getBalance() - balance;
        account.setBalance(total);
        Account saveaccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(saveaccount);

    }

    @Override
    public List<AccountDto> getAllaccounts() {
        List<Account> account = accountRepository.findAll();
        return account.stream().map((account1) -> AccountMapper.mapToAccountDto(account1))
                .collect(Collectors.toList());
//        return account;
    }

    @Override
    public AccountDto deleteAccount(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
        accountRepository.deleteById(id);
        return  AccountMapper.mapToAccountDto(account);


    }

}
