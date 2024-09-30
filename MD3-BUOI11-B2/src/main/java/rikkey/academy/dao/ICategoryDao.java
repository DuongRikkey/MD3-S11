package rikkey.academy.dao;

import java.util.List;

public interface ICategoryDao <T,E>{
    void addAndUpdate(T t);
    void remove(E id);
    int findIndexByID(E id);
    E getNewID();
    List<T> findAll();
}
