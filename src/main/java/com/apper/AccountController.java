package com.apper;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateAccountResponse createAccount(@RequestBody CreateAccountRequest request) {
        Account account= accountService.create(request.getFirstName(), request.getLastName(), request.getEmail(), request.getPassword());

        CreateAccountResponse response = new CreateAccountResponse();
        response.setVerificationCode(account.getVerificationCode());

        return response;
    }

    @GetMapping("{accountId}")
    public GetAccountResponse getAccount(@PathVariable String accountId) {
        Account account = accountService.get(accountId);

        return createGetAccountResponse(account);
    }

    @GetMapping
    public List<GetAccountResponse> getAllAccounts() {
        List<GetAccountResponse> responseList = new ArrayList<>();

        for (Account account : accountService.getAll()) {
            GetAccountResponse response = createGetAccountResponse(account);
            responseList.add(response);
        }

        return responseList;
    }

    private GetAccountResponse createGetAccountResponse(Account account) {
        GetAccountResponse response = new GetAccountResponse();
        response.setBalance(account.getBalance());
        response.setFirstName(account.getFirstName());
        response.setLastName(account.getLastName());
        response.setUsername(account.getUsername());
        response.setRegistrationDate(account.getCreationDate());
        response.setAccountId(account.getId());
        return response;
    }

    @PutMapping("{accountId}")
    public UpdateAccountResponse updateAccount(@RequestBody CreateAccountRequest request, @PathVariable String accountId) {
        //reusing CreateAccountRequest
        Account account = accountService.get(accountId);
        account.setFirstName(request.getFirstName());
        account.setLastName(request.getLastName());
        account.setUsername(request.getEmail());
        account.setClearPassword(request.getPassword());

        UpdateAccountResponse response = new UpdateAccountResponse();
        response.setLastUpdated(account.getLastUpdated());
        return response;
    }

    @DeleteMapping("{accountId}")
    @ResponseStatus(HttpStatus.NO_CONTENT) //don't forget to put this to give the proper response
    public void deleteAccount(@PathVariable String accountId) {
        List<Account> accounts = accountService.getAll();

        for (Account account: accounts) {
            if (account.getId().equals(accountId)) {
                accounts.remove(account);
                break;
            }
        }
    }
}