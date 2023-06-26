package com.apper;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AccountService {

    private List<Account> accounts = new ArrayList<>();

    public Account create(String firstName, String lastName, String username, String clearPassword) {
        Account account = new Account();

        IdGeneratorService IdGenServ = new IdGeneratorService();
        account.setId(IdGenServ.getNextId());
        //like what sir said, segregation of concern, dapat di concerned si AccountService with creating the id and its format

        account.setBalance(1_000.0); //you can use underscore to separate big numbers, parang comma

        LocalDateTime now = LocalDateTime.now();
        account.setCreationDate(now);
        account.setLastUpdated(now);

        account.setFirstName(firstName);
        account.setLastName(lastName);
        account.setUsername(username);
        account.setClearPassword(clearPassword);
        account.setVerificationCode(IdGenServ.generateRandomCharacters(6));

        accounts.add(account);

        return account;
    }

    public Account get(String accountId) {
        for (Account account : accounts) {
            if (account.getId().equals(accountId)) {
                return account;
            }
        }

        return null;
    }

    public List<Account> getAll() {
        return accounts;
    }
//
//    public void update() {
//
//    }
//
//    public void delete() {
//
//    }
}