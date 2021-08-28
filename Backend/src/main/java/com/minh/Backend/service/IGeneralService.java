package com.minh.Backend.service;

import java.util.Collection;

public interface IGeneralService<T> {
    Collection<T> findAll();

    T findById(Integer id);

    T save(T t);

    void remove(Integer id);
}
