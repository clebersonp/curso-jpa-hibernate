package com.algaworks.curso.jpa2.dao;

import java.util.List;

import com.algaworks.curso.jpa2.service.NegocioException;

public interface PersistDAO<T> {

	public void salvar(T t);
	public void excluir(T t) throws NegocioException;
	public List<T> buscarTodos();
	public T buscarPeloCodigo(Long codigo);
	
}
