package com.algaworks.curso.jpa2.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.algaworks.curso.jpa2.modelo.Fabricante;
import com.algaworks.curso.jpa2.util.jpa.Transactional;

public class FabricanteDAO implements PersistDAO<Fabricante>,Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager entityManager;
	
	@Transactional
	@Override
	public void salvar(Fabricante fabricante) {
		this.entityManager.merge(fabricante);
	}

	@Override
	public List<Fabricante> buscarTodos() {
		return this.entityManager.createQuery("from Fabricante", Fabricante.class)
				.getResultList();
	}

	@Transactional
	@Override
	public void excluir(Fabricante fabricante) {
		//pesquisando o fabricante antes de excluir
		//para atachalo no entity manager
		fabricante = this.entityManager.find(Fabricante.class, fabricante.getCodigo());
		this.entityManager.remove(fabricante);
		this.entityManager.flush();
	}

	@Override
	public Fabricante buscarPeloCodigo(Long codigo) {
		return this.entityManager.find(Fabricante.class, codigo);
	}
	
}
