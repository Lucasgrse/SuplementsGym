package com.suplements.gym.serviceImplement;

import com.suplements.gym.Repository.User;
import com.suplements.gym.Utils.SuplementGymUtils;
import com.suplements.gym.constents.SuplementGymContent;
import com.suplements.gym.dao.UserDao;
import com.suplements.gym.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
public class UserServiceImplement implements UserService {

    @Autowired
    UserDao userDao;


    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        log.info("Inside signup {}", requestMap);
        try{
            if (validationSignUpMap(requestMap)) {
                User user = userDao.findByEmailId(requestMap.get("email"));
                if(Objects.isNull(user)){
                    userDao.save(getUserFromMap(requestMap));
                    return SuplementGymUtils.getResponseEntity("Cadastro realizado com sucesso!", HttpStatus.OK);
                }else{
                    return SuplementGymUtils.getResponseEntity("Esse email já está em uso!", HttpStatus.BAD_REQUEST);
                    }
            }else{
                return SuplementGymUtils.getResponseEntity(SuplementGymContent.INVALID_DATE, HttpStatus.BAD_REQUEST);
                }
        }catch(Exception ex){

        }
            return SuplementGymUtils.getResponseEntity(SuplementGymContent.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private boolean validationSignUpMap(Map<String, String> requestMap){
        if(requestMap.containsKey("name") && requestMap.containsKey("contactNumber")
                && requestMap.containsKey("email") && requestMap.containsKey("password")){
            return true;
        } else{
            return false;
        }
    }

    private User getUserFromMap(Map<String, String> requestMap){
        User user = new User();
        user.setName(requestMap.get("name"));
        user.setContactNumber(requestMap.get("contactNumber"));
        user.setEmail(requestMap.get("email"));
        user.setPassword(requestMap.get("password"));
        user.setStatus("false");
        user.setRole("user");
        return user;


    }
}
