package com.algaworks.jsf;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class MeuBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String nome;
	
	private String nomeAlterado;

	public void transformar() {
		this.nomeAlterado = this.nome.toUpperCase();
	}
	
	public void limpar() {
		this.nome = "";
		this.nomeAlterado = "";
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeAlterado() {
		return nomeAlterado;
	}
	public void setNomeAlterado(String nomeAlterado) {
		this.nomeAlterado = nomeAlterado;
	}
}
