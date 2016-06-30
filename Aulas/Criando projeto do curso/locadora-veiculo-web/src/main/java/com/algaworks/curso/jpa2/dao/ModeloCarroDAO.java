package com.algaworks.curso.jpa2.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.algaworks.curso.jpa2.modelo.ModeloCarro;
import com.algaworks.curso.jpa2.util.jpa.Transactional;

public class ModeloCarroDAO implements PersistDAO<ModeloCarro>,Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	EntityManager entityManager;
	
	@Transactional
	@Override
	public void salvar(ModeloCarro modeloCarro) {
		this.entityManager.merge(modeloCarro);
	}
	
	@Override
	public List<ModeloCarro> buscarTodos() {
		return this.entityManager.createQuery("from ModeloCarro", ModeloCarro.class)
				.getResultList();
	}

	@Transactional
	@Override
	public void excluir(ModeloCarro modeloCarro) {
		modeloCarro = this.buscarPeloCodigo(modeloCarro.getCodigo());
		this.entityManager.remove(modeloCarro);
		this.entityManager.flush();
	}
	
	@Override
	public ModeloCarro buscarPeloCodigo(Long codigo) {
		return this.entityManager.find(ModeloCarro.class, codigo);
	}
	

}
