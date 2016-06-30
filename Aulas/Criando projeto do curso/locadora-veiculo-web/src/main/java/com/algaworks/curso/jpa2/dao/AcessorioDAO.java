package com.algaworks.curso.jpa2.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.algaworks.curso.jpa2.modelo.Acessorio;
import com.algaworks.curso.jpa2.util.jpa.Transactional;

public class AcessorioDAO implements PersistDAO<Acessorio>, Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;
	
	@Transactional
	@Override
	public void salvar(Acessorio acessorio) {
		this.manager.merge(acessorio);
	}

	@Transactional
	@Override
	public void excluir(Acessorio acessorio) {
		Acessorio outro = this.buscarPeloCodigo(acessorio.getCodigo());
		this.manager.remove(outro);
		this.manager.flush();
	}

	@Override
	public List<Acessorio> buscarTodos() {
		return this.manager.createQuery("from Acessorio", Acessorio.class)
				.getResultList();
	}

	@Override
	public Acessorio buscarPeloCodigo(Long codigo) {
		return this.manager.find(Acessorio.class, codigo);
	}

}
