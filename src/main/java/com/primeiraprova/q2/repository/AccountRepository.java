package com.primeiraprova.q2.repository;

import com.primeiraprova.q2.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {
    List<Account> findAll();
    Account findById(int id);
    List<Account> findByName(String name);
}
