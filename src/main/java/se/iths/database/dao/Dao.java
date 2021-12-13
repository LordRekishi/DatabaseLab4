package se.iths.database.dao;

import java.util.List;

public interface Dao<T> {
    void insert(T t);
    void delete(T t);
    void update(T t);
    T getByID(int id);
    List<T> getByName(String name);
    List<T> getAll();
}
