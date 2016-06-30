package com.algaworks.curso.jpa2.dao;

import java.util.List;

public interface PersistDAO<T> {

	public void salvar(T t);
	public void excluir(T t);
	public List<T> buscarTodos();
	public T buscarPeloCodigo(Long codigo);
	
}
