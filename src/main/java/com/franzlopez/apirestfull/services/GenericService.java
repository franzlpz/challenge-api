package com.franzlopez.apirestfull.services;

import java.util.List;
import java.util.Optional;

public interface GenericService<T, V> {
    Optional<T> findById(V id);
    List<T> findAll();
    void deleteById(V id);

}
