package com.franzlopez.apirestfull.repositories;

import com.franzlopez.apirestfull.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User,String> {
    Optional<User> findByEmail(String email);
}
