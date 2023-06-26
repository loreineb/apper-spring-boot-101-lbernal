package com.apper;
import java.util.UUID;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
//don't forget to mvn clean install para for the apache dependency you added

@Service //to make it a bean
public class IdGeneratorService {

    public String generateRandomCharacters(int length) {
        String verificationCode = RandomStringUtils.randomAlphanumeric(length);
        //System.out.println(verificationCode);
        return verificationCode;
    }

    public String getNextId(){
        String id = UUID.randomUUID().toString();
        System.out.println("Generated id: " + id);
        return id;
    }
}
