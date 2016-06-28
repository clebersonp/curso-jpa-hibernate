package com.algaworks.curso.jpa2.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.curso.jpa2.dao.FabricanteDAO;
import com.algaworks.curso.jpa2.modelo.Fabricante;
import com.algaworks.curso.jpa2.modelo.ModeloCarro;
import com.algaworks.curso.jpa2.service.CadastroModeloCarroService;
import com.algaworks.curso.jpa2.service.NegocioException;
import com.algaworks.curso.jpa2.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroModeloCarroBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CadastroModeloCarroService cadastroModeloCarroService;
	@Inject
	private FabricanteDAO fabricanteDAO;
	private ModeloCarro modeloCarro;
	private List<Fabricante> fabricantes;
	
	@PostConstruct
	public void init() {
		this.limpar();
		this.fabricantes = this.fabricanteDAO.buscarTodos();
	}
	
	public void limpar() {
		this.modeloCarro = new ModeloCarro();
	}
	
	public void salvar() {
		try {
			this.cadastroModeloCarroService.salvar(this.modeloCarro);
			FacesUtil.addSuccessMessage("Modelo " + this.modeloCarro.getDescricao() + " foi salvo com sucesso!");
			this.limpar();
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public ModeloCarro getModeloCarro() {
		return modeloCarro;
	}
	public void setModeloCarro(ModeloCarro modeloCarro) {
		this.modeloCarro = modeloCarro;
	}
	
	public List<Fabricante> getFabricantes() {
		return this.fabricantes;
	}
}
