package com.example.demo.controller;

import com.example.demo.dto.AccountDto;
import com.example.demo.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private AccountService accountService;
    private Map<String, Object> request;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    //add account api
    @PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto){
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }
    //get accountsbyid
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountByid(@PathVariable Long id){
        AccountDto accountDto = accountService.getAccountById(id);
        return  ResponseEntity.ok(accountDto);
    }
    //deposit
    @PutMapping("/{id}/deposit")
    public  ResponseEntity<AccountDto> deposit(@PathVariable Long id,@RequestBody Map<String, Object> request){
        double amount = ((Number) request.get("amount")).doubleValue();
        AccountDto accountDto = accountService.deposit(id,amount);
        return ResponseEntity.ok(accountDto);
    }
    @PutMapping("/{id}/withdraw")
    public  ResponseEntity<AccountDto> withdraw(@PathVariable Long id,@RequestBody Map<String, Object> request){
        double amount = ((Number) request.get("amount")).doubleValue();
        AccountDto accountDto = accountService.withdraw(id,amount);
        return ResponseEntity.ok(accountDto);
    }
    //get all accounts
    @GetMapping
    public ResponseEntity<List<AccountDto>> getallaccounts(){
        List<AccountDto> accountDto = accountService.getAllaccounts();
        return ResponseEntity.ok(accountDto);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id){
        AccountDto accountDto = accountService.deleteAccount(id);
        return ResponseEntity.ok("Account is deleted successfully!");
    }

}
