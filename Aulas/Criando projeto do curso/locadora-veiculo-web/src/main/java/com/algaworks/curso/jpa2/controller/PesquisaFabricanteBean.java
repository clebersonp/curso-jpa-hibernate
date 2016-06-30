package com.algaworks.curso.jpa2.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.curso.jpa2.dao.FabricanteDAO;
import com.algaworks.curso.jpa2.modelo.Fabricante;
import com.algaworks.curso.jpa2.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaFabricanteBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private FabricanteDAO fabricanteDAO;
	private List<Fabricante> fabricantes;
	private Fabricante fabricanteSelecionado;

	@PostConstruct
	public void init() {
		this.fabricantes = this.fabricanteDAO.buscarTodos();
	}

	public void excluir() {
		try {
			fabricanteDAO.excluir(this.fabricanteSelecionado);
			this.fabricantes.remove(this.fabricanteSelecionado);
			FacesUtil.addSuccessMessage("Fabricante " 
					+ this.fabricanteSelecionado.getNome() 
					+ " excluído com sucesso!");
		} catch (Exception e) {
			FacesUtil.addErrorMessage("O fabricante "
					+ this.fabricanteSelecionado.getNome()
					+ " não foi excluído!");
		}
	}
	
	public Fabricante getFabricanteSelecionado() {
		return fabricanteSelecionado;
	}
	public void setFabricanteSelecionado(Fabricante fabricanteSelecionado) {
		this.fabricanteSelecionado = fabricanteSelecionado;
	}

	public List<Fabricante> getFabricantes() {
		return this.fabricantes;
	}
}