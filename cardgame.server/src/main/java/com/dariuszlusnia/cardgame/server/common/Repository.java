package com.dariuszlusnia.cardgame.server.common;

import java.util.List;

public interface Repository<T> {
    void add(T entity);
    void update(T entity);
    List<T> getAll();
    T get(String id);
    void delete(String id);
}