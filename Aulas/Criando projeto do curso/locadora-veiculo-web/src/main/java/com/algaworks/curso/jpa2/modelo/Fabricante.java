package com.algaworks.curso.jpa2.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Fabricante {

	private Long codigo;
	private String nome;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	/* Preciso implementar o equals e hasCode por
	 * causa do converter de fabricante.
	 * No comboBox de fabricante no cadastro do modelo do carro,
	 * o converter vai converter o valor do fabricante no html
	 * para um objeto fabricante pelo codigo, e o jsf vai comparar
	 * se o objeto que retornou da consulta é igual ao da pagina html,
	 * como o jsf sabe que são iguais?
	 * pelo código, pois no equals e hasCode eu falo qual atributo
	 * que quero comparar entre dois objetos
	 */
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fabricante other = (Fabricante) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
}
