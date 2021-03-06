package com.algaworks.curso.jpa2.modelo;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Aluguel {

	private Long codigo;
	private BigDecimal valorTotal;
	private Carro carro;
	private ApoliceSeguro apoliceSeguro;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	@Column(name = "valor_total")
	public BigDecimal getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	@ManyToOne
	@JoinColumn(name = "codigo_carro")
	public Carro getCarro() {
		return carro;
	}
	public void setCarro(Carro carro) {
		this.carro = carro;
	}

	/*
	 * Retirando o cascade all, eu preciso primeiro salvar a apolice
	 * para dps salvar o alguel no banco, dessa maneira seria melhor persistir
	 * a apolice na camada dao.
	 * */
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "codigo_apolice_seguro")
	public ApoliceSeguro getApoliceSeguro() {
		return apoliceSeguro;
	}
	public void setApoliceSeguro(ApoliceSeguro apoliceSeguro) {
		this.apoliceSeguro = apoliceSeguro;
	}

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
		Aluguel other = (Aluguel) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
}
