package com.wildcodeschool.synergieFamily.service;

import org.springframework.stereotype.Service;

@Service
public class TokenService {

    public String randomToken(int length, int digits) {

        String password = "";
        for (int i = 0; i < length - digits; i++) {

            password = password + randomCharacter("abcdefghijklmnopqrstuvwxyz");
        }

        for (int i = 0; i < digits; i++) {

            String randomDigit = randomCharacter(("0123456789"));
            password = insertStringAtRandomPosition(password, randomDigit);
        }
        return password;
    }

    private String randomCharacter(String characters) {

        int length = characters.length();
        int randomIndex = (int) (length * Math.random());
        return characters.substring(randomIndex, randomIndex + 1);
    }

    private String insertStringAtRandomPosition(String stringDestination, String stringToInsert) {

        int length = stringDestination.length();
        int randomIndex = (int) ((length + 1) *  Math.random());
        return stringDestination.substring(0, randomIndex) + stringToInsert + stringDestination.substring(randomIndex);
    }
}
