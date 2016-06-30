package com.algaworks.curso.jpa2.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.algaworks.curso.jpa2.dao.AcessorioDAO;
import com.algaworks.curso.jpa2.modelo.Acessorio;

public class CadastroAcessorioService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private AcessorioDAO acessorioDAO;
	
	public void salvar(Acessorio acessorio) throws NegocioException {
		if (acessorio.getDescricao() == null || ("".equals(acessorio.getDescricao().trim()))) {
			throw new NegocioException("A descrição do acessório é obrigatória!");
		}
		
		this.acessorioDAO.salvar(acessorio);
		
	}

}
