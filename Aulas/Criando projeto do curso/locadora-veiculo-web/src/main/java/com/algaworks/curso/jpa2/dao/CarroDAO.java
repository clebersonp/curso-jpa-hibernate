package com.algaworks.curso.jpa2.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.algaworks.curso.jpa2.modelo.Carro;
import com.algaworks.curso.jpa2.util.jpa.Transactional;

public class CarroDAO implements PersistDAO<Carro>, Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	@Transactional
	@Override
	public void salvar(Carro carro) {
		this.manager.merge(carro);
		
	}

	@Transactional
	@Override
	public void excluir(Carro carro) {
		Carro outro = this.buscarPeloCodigo(carro.getCodigo());
		this.manager.remove(outro);
		this.manager.flush();
	}

	@Override
	public List<Carro> buscarTodos() {
		return this.manager.createQuery("from Carro", Carro.class)
				.getResultList();
	}

	@Override
	public Carro buscarPeloCodigo(Long codigo) {
		return this.manager.find(Carro.class, codigo);
	}
	
	
}
