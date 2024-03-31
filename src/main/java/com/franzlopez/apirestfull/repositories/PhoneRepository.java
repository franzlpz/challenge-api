package com.franzlopez.apirestfull.repositories;

import com.franzlopez.apirestfull.models.Phone;
import org.springframework.data.repository.CrudRepository;

public interface PhoneRepository extends CrudRepository<Phone,String> {
}
