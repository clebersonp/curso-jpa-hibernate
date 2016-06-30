package com.algaworks.curso.jpa2.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.curso.jpa2.dao.AcessorioDAO;
import com.algaworks.curso.jpa2.dao.ModeloCarroDAO;
import com.algaworks.curso.jpa2.modelo.Acessorio;
import com.algaworks.curso.jpa2.modelo.Carro;
import com.algaworks.curso.jpa2.modelo.ModeloCarro;
import com.algaworks.curso.jpa2.service.CadastroCarroService;
import com.algaworks.curso.jpa2.service.NegocioException;
import com.algaworks.curso.jpa2.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroCarroBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CadastroCarroService carroService;
	
	@Inject
	private AcessorioDAO acessorioDAO;
	
	@Inject
	private ModeloCarroDAO modeloCarroDAO;
	
	private Carro carro;
	private List<Acessorio> acessorios;
	private List<ModeloCarro> modelosCarros;
	
	@PostConstruct
	public void init() {
		this.limpar();
		this.acessorios = this.acessorioDAO.buscarTodos();
		this.modelosCarros = this.modeloCarroDAO.buscarTodos();
	}
	
	
	private void limpar() {
		this.carro = new Carro();
		this.carro.setAcessorios(new ArrayList<>());
	}
	
	public void salvar() {
		
		try {
			this.carroService.salvar(this.carro);
			FacesUtil.addSuccessMessage("Carro salvo com sucesso!");
			this.limpar();
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
		
	}

	public Carro getCarro() {
		return carro;
	}
	public void setCarro(Carro carro) {
		this.carro = carro;
	}
	
	public List<Acessorio> getAcessorios() {
		return acessorios;
	}

	public List<ModeloCarro> getModelosCarros() {
		return modelosCarros;
	}
}
