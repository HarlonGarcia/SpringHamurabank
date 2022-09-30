package com.primeiraprova.q2.service;

import com.primeiraprova.q2.model.Account;
import com.primeiraprova.q2.model.User;
import com.primeiraprova.q2.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;

    public Account createAccount(Account account) {
        if (account != null && !verifyUser(account.getName())) {
            accountRepository.save(account);
        } else {
            account = null;
        }

        return account;
    }

    public boolean verifyUser(String name) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<User[]> response = restTemplate.getForEntity("https://jsonplaceholder.typicode.com/users", User[].class);
        List<User> users = Arrays.stream(Objects.requireNonNull(response.getBody())).toList();

        boolean isWanted = false;

        for (User user: users) {
            if (user != null && user.getName().equals(name)) {
                isWanted = true;
                break;
            }
        }

        return isWanted;
    }

    public Account updateAccount(Account accountWithChanges) {
        Account account = accountRepository.findById(accountWithChanges.getId());

        if (account != null) {
            account.setName(accountWithChanges.getName());
            account.setAgency(accountWithChanges.getAgency());
            account.setNumber(accountWithChanges.getNumber());
            accountRepository.save(account);
        }

        return account;
    }

    public List<Account> findAll() {
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
