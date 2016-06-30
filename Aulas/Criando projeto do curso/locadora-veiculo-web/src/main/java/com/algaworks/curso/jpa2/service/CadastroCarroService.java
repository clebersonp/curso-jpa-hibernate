package com.algaworks.curso.jpa2.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.algaworks.curso.jpa2.dao.CarroDAO;
import com.algaworks.curso.jpa2.modelo.Carro;

public class CadastroCarroService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CarroDAO carroDAO;
	
	public void salvar(Carro carro) throws NegocioException {
		if (carro.getPlaca() == null || ("".equals(carro.getPlaca().trim()))) {
			throw new NegocioException("A placa do carro é obrigatória!");
		}
		this.carroDAO.salvar(carro);
	}
}
