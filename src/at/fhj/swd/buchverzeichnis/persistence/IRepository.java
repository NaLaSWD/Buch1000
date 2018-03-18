package at.fhj.swd.buchverzeichnis.persistence;

import java.util.List;

public interface IRepository<T> {
	T find (int id);
    List<T> findAll();
}

