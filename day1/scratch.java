/*
Spring framework:
- response to complexity of Java Enterprise Edition
- reduces boilerplate codes so you can focus more on business implementation
    - getter and setters r the most common boilerplates
- remove the paulit-ulit na code, parang TLDR

Spring Boot:
- you can choose, walang coding or wtvr, nilagay mo lang sa spring boot na project
- easier to deploy and get components
- flexible, modular, backwards compatible
1. autoconfiguration: set-up automagically; configures based on dependencies
2. standalone: no need for servers; production-grade
3. opinionated: has chosen way of doing things by default; designed to get you up and running asap

artifact: yung first identity niya

process ID: if you want to stop smth
si Spring Boot na rin si Tomcat
pag web application, laging may port na involved for access
to reboot it again, need to patay talaga kasi may tumatakbo daw: naiwan yung kanina
can do this: mvn spring-boot:run

dependency injection: you request then ginagawa na: handles all initialization of classes
* refresher: class vs objects -> class di pa pwedeng gamitin

if you put @Service, maiinitialize na siya and ma-aadd sa spring context
    -but @Service isn't the only way to do dependency injection
 */
*/

package com.gcash.service.creditservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//App
@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
    @Bean
    public CommandLineRunner helloWorld() {
        return args -> System.out.println("Hello, World!");
    }

}

package com.gcash.service.creditservice;

        import java.util.ArrayList;
        import java.util.List;
        import java.util.UUID;

//CreditService
public class CreditService {

    private List<Account> accounts = new ArrayList<>();

    public Account createAccount(Double initialBalance) {
        String accountId = UUID.randomUUID().toString();

        Account account = new Account();
        account.setId(accountId);
        account.setBalance(initialBalance);

        return account;
    }

    public List<Account> getAllAccounts() {
        return accounts;
    }

    public void addBalance(String accountId, Double amount) {
        for (Account account : accounts) {
            if (account.getId().equals(accountId)) {
                double newBalance = account.getBalance() + amount;
                account.setBalance(newBalance);
                return;
            }
        }
    }
}

//CreditApi
package com.gcash.service.creditservice;

        import org.springframework.web.bind.annotation.RestController;

        import java.util.List;

@RestController
public class CreditApi {

    private final CreditService creditService;

    public CreditApi(CreditService creditService) {
        this.creditService = creditService;
    }

    //create an account
    public Account createNewAccount(Double initialBalance) {
        return creditService.createAccount(initialBalance);
    }

    // retrieve all accounts
    public List<Account> getAllAccounts() {
        return creditService.getAllAccounts();
    }

}

//Account
package com.gcash.service.creditservice;

        import lombok.Data;

@Data
public class Account {
    private String id;
    private Double balance;
}
