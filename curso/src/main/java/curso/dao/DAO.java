package curso.dao;

import java.util.List;

import curso.exception.DAOException;

public interface DAO<T, K>  {
	public void add(K element) throws DAOException;

	public K get(T key)throws DAOException;

	public List<K> getAll()throws DAOException;

	public void uptade(K element)throws DAOException;

	public boolean remove(T key)throws DAOException;
}
