package com.apper;

import lombok.Data;

@Data //do not forget this: pag wala toh, mag sesetter getter ka pa
public class CreateAccountResponse {
    private String verificationCode;
}