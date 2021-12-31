package com.product.dao;

import java.util.List;

public interface AbstractDao<T> {
    void createSmartphone(T entity);
    List<T> getAll();
    T getById(int id);
    void update(T entity);
    void delete(int id);
}
