package com.juls.persist;

import java.util.List;

public interface IDAO<T> {
	public List<T> getAll();
	public T getById(String id);
	public boolean insert(T value);
	public boolean update(T value);
	public boolean delete(T value);
}
