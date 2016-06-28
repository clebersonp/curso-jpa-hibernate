package com.algaworks.curso.jpa2.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.algaworks.curso.jpa2.dao.ModeloCarroDAO;
import com.algaworks.curso.jpa2.modelo.ModeloCarro;

public class CadastroModeloCarroService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	ModeloCarroDAO modeloCarroDAO;
	
	public void salvar(ModeloCarro modeloCarro) throws NegocioException {
		if (modeloCarro.getDescricao() == null || modeloCarro.getDescricao().trim().equals("")) {
			throw new NegocioException("O modelo do carro deve ser informado!");
		}

		if (modeloCarro.getFabricante() == null ) {
			throw new NegocioException("O fabricante é obrigatório!");
		}
		
		this.modeloCarroDAO.salvar(modeloCarro);
	}
}
