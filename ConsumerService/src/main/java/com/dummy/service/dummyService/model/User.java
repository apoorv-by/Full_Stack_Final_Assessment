package com.dummy.service.dummyService.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;


@Data
@NoArgsConstructor
public class User {

    private ObjectId id;
    private String username;

    private String email;

    private String password;

    public User(ObjectId id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":\"" + id +"\""+
                ", \"username\":\"" + username + '\"' +
                ", \"email\":\"" + email + '\"' +
                ", \"password\":\"" + password + '\"' +
                '}';
    }
}
