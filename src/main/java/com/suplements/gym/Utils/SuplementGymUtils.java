package com.suplements.gym.Utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class SuplementGymUtils {

    private SuplementGymUtils(){

    }

    public static ResponseEntity<String> getResponseEntity(String responseMessage, HttpStatus httpStatus){
        return new ResponseEntity<String>("{\"message\":\""+responseMessage+"\"}", httpStatus);
    }
}
