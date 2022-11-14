package com.authentication.service.authentication.service;


import com.authentication.service.authentication.exceptionHandling.UserException;
import com.authentication.service.authentication.model.User;
import com.authentication.service.authentication.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    public String loginService(Map<String,Object> userData) throws Exception {
        String username = isValid("username","Username is invalid.",userData,HttpStatus.NOT_ACCEPTABLE);
        String password = isValid("password","Password is invalid.",userData,HttpStatus.NOT_ACCEPTABLE);

        User foundUsers =  repository.getUsersByName(username);

        if(foundUsers==null){
            throw new UserException("Username is invalid!!").setDefaultException(HttpStatus.NOT_FOUND);
        }

        else if(!foundUsers.getPassword().equals(password)){
            throw new UserException("Password incorrect!");
        }
        return "{" +
                "\"message\":"+"\" Successfully Login. \","+
                "\"status\":true"+
                "}";
    }


    private String isValid(String key, String errorMessage, Map<String,Object> data, HttpStatus httpStatus) throws Exception {
        Object stringData = data.get(key);
        if(stringData==null)    throw new UserException(errorMessage).setDefaultException(httpStatus);
        return stringData.toString();
    }

    private User isDataValid(Map<String,Object> data) throws Exception {
        String username = isValid("username","Username is invalid.",data,HttpStatus.NOT_ACCEPTABLE);
        String password = isValid("password","Password is invalid.",data,HttpStatus.NOT_ACCEPTABLE);
        String confirmPassword = isValid("confirm password","Confirm password  is invalid.",data,HttpStatus.NOT_ACCEPTABLE);
        String email = isValid("email","Email is invalid.",data,HttpStatus.NOT_ACCEPTABLE);

        if(!password.equals(confirmPassword))    throw new UserException("Password missed match.").setDefaultException(HttpStatus.UNAUTHORIZED);

        return new User(null,username,email,password);
    }

    public String signUp(Map<String,Object> userData) throws Exception {

        User user = isDataValid(userData);

        User userInRepo = repository.getUsersByName(user.getUsername());
        if(userInRepo!=null)    throw new UserException("Username already exist! [Method not allowed]").setDefaultException(HttpStatus.METHOD_NOT_ALLOWED);

        user = repository.save(user);
        return "{" +
                "\"message\":"+"\"Successfully Created A User\","+
                "\"data\":"+user+"}";
    }


    public List<User> getAll(){
        System.out.println(repository.findAll());
        return repository.findAll();
    }
}
