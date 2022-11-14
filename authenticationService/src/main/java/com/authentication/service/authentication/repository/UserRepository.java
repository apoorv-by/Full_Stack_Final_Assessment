package com.authentication.service.authentication.repository;


import com.authentication.service.authentication.model.User;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public interface UserRepository extends MongoRepository<User,Integer> {
    @Query("{username:\"?0\"}")
    public User getUsersByName(String username);
}
