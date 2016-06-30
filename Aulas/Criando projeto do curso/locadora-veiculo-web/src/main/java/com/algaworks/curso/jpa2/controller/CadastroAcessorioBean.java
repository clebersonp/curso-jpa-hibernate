package com.algaworks.curso.jpa2.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.curso.jpa2.modelo.Acessorio;
import com.algaworks.curso.jpa2.service.CadastroAcessorioService;
import com.algaworks.curso.jpa2.service.NegocioException;
import com.algaworks.curso.jpa2.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroAcessorioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Acessorio acessorio;
	
	@Inject
	private CadastroAcessorioService acessorioService;

	@PostConstruct
	public void init() {
		this.limpar();
	}
	
	public void salvar() {
		try {
			this.acessorioService.salvar(this.acessorio);
			this.limpar();
			FacesUtil.addSuccessMessage("Acessório salvo com sucesso!");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}
	
	private void limpar() {
		this.acessorio = new Acessorio();
	}
	
	public Acessorio getAcessorio() {
		return acessorio;
	}
	public void setAcessorio(Acessorio acessorio) {
		this.acessorio = acessorio;
	}
}
