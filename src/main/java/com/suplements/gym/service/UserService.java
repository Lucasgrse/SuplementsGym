package com.suplements.gym.service;

import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface UserService {

    ResponseEntity<String> signUp(Map<String, String> requestMap);

}
