package com.algaworks.curso.jpa2.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.curso.jpa2.dao.AcessorioDAO;
import com.algaworks.curso.jpa2.modelo.Acessorio;

@Named
@ViewScoped
public class PesquisaAcessorioBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private AcessorioDAO acessorioDAO;
	
	private Acessorio acessorioSelecionado;
	
	private List<Acessorio> acessorios;
	
	@PostConstruct
	public void init() {
		this.acessorios = this.atualizarTabela();
	}

	private List<Acessorio> atualizarTabela() {
		return this.acessorioDAO.buscarTodos();
	}

	public void excluir() {
		this.acessorioDAO.excluir(this.acessorioSelecionado);
		this.acessorios.remove(this.acessorioSelecionado);
		this.atualizarTabela();
	}
	
	public List<Acessorio> getAcessorios() {
		return acessorios;
	}

	public Acessorio getAcessorioSelecionado() {
		return acessorioSelecionado;
	}
	public void setAcessorioSelecionado(Acessorio acessorioSelecionado) {
		this.acessorioSelecionado = acessorioSelecionado;
	}
}
