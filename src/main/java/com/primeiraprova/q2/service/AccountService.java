package com.primeiraprova.q2.service;

import com.primeiraprova.q2.model.Account;
import com.primeiraprova.q2.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    public Account createAccount(Account account) {
        if (account != null) {
            accountRepository.save(account);
        }

        return account;
    }

    public Account updateAccount(Account accountWithChanges) {
        Account account = accountRepository.findById(accountWithChanges.getId());

        if (account != null) {
            accountRepository.save(account);
        }

        return account;
    }

    public List<Account> findAccountAll() {
        return accountRepository.findAll();
    }

    public Account findAccountById(int id) {
        return accountRepository.findById(id);
    }

    public List<Account> findAccountByName(String name) {
        return accountRepository.findByName(name);
    }

    public void deleteAccount(int id) throws Exception {
        Account account = accountRepository.findById(id);

        if (account != null) {
            accountRepository.delete(account);
            return;
        }

        throw new Exception();
    }
}
