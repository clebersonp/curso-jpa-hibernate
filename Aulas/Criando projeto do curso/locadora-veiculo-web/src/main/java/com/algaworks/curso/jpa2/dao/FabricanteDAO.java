package com.algaworks.curso.jpa2.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.algaworks.curso.jpa2.modelo.Fabricante;
import com.algaworks.curso.jpa2.service.NegocioException;
import com.algaworks.curso.jpa2.util.jpa.Transactional;

public class FabricanteDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;
	
	@Transactional
	public void salvar(Fabricante fabricante) {
		em.persist(fabricante);
	}

	public List<Fabricante> buscarTodos() {
		return this.em.createQuery("from Fabricante", Fabricante.class)
				.getResultList();
	}

	@Transactional
	public void excluir(Fabricante fabricante) throws NegocioException {
		//pesquisando o fabricante antes de excluir
		//para atachalo no entity manager
		fabricante = this.em.find(Fabricante.class, fabricante.getCodigo());
		this.em.remove(fabricante);
		this.em.flush();
	}
	
}
