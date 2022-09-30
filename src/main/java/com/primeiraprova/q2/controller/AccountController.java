package com.primeiraprova.q2.controller;

import com.primeiraprova.q2.model.Account;
import com.primeiraprova.q2.service.AccountService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@Getter
@Setter
@RequestMapping("/account")
public class AccountController {
    @Autowired
    AccountService accountService;

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        ResponseEntity<Account> response = new ResponseEntity<Account>(HttpStatus.BAD_REQUEST);
        Account accountCreated = accountService.createAccount(account);

        if (accountCreated != null) {
            response = new ResponseEntity<>(accountCreated, HttpStatus.CREATED);
        }
        return response;
    }

    @GetMapping
    public ResponseEntity<List<Account>> findAll() {
        return new ResponseEntity<List<Account>>(accountService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> findAccountById(@PathVariable int id) {
        Account account = accountService.findAccountById(id);
        ResponseEntity<Account> response = new ResponseEntity<Account>(HttpStatus.NO_CONTENT);

        if (account != null) {
            response = new ResponseEntity<Account>(account, HttpStatus.OK);
        }

        return response;
    }

    @GetMapping("/q")
    public ResponseEntity<List<Account>> findAccountByName(@RequestParam String name) {
        List<Account> allAccounts = accountService.findAccountByName(name);
        ResponseEntity<List<Account>> response = new ResponseEntity<List<Account>>(HttpStatus.OK);

        if (allAccounts != null) {
            response = new ResponseEntity<List<Account>>(allAccounts, HttpStatus.OK);
        }

        return response;
    }

    @PutMapping
    public ResponseEntity<Account> findAccountByName(@RequestBody Account accountWithChanges) {
        Account account = accountService.updateAccount(accountWithChanges);
        ResponseEntity<Account> response = new ResponseEntity<Account>(HttpStatus.BAD_REQUEST);

        if (account != null) {
            response = new ResponseEntity<Account>(account, HttpStatus.OK);
        }

        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable int id) {
        ResponseEntity<Void> response;

        try {
            accountService.deleteAccount(id);
            response = new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return response;
    }
}
