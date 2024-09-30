package rikkey.academy.service;

import java.util.List;

public interface IGenericDesign <T,E> {
    void addAndUpdate(T t);
    void remove(E id);
    int findIndexByID(E id);
    E getNewID();
    List<T> findAll();
}
