package com.example.ejb.daoRemote;

//import javax.ejb.Remote;
//
//@Remote
public interface GenericDAO<T> {
	void insert(T data);
	void update(T data);
	void delete(T data);
}
