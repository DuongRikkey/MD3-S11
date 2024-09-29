package rikkey.academy.service;

public interface IgenericService <T,E>{
    void addAndUpdate(T t);
    void delete(E id);
    int findIndexByID(E id);
    E getNewID();
}
