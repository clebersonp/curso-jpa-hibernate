package com.algaworks.curso.jpa2.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.algaworks.curso.jpa2.dao.AluguelDAO;
import com.algaworks.curso.jpa2.modelo.Aluguel;

public class CadastroAluguelService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private AluguelDAO aluguelDAO;
	
	public void salvar(Aluguel aluguel) throws NegocioException {
		if (aluguel.getCarro() == null) {
			throw new NegocioException("Carro é obrigatório!");
		}
		this.aluguelDAO.salvar(aluguel);
	}
}
