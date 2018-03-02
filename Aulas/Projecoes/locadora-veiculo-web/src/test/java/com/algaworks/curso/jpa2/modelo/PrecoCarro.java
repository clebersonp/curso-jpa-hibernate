package com.algaworks.curso.jpa2.modelo;

import java.math.BigDecimal;

public class PrecoCarro {

	private String placa;
	private BigDecimal valorDiaria;

	public PrecoCarro(String placa, BigDecimal valorDiaria) {
		super();
		this.placa = placa;
		this.valorDiaria = valorDiaria;
	}
	
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	
	public BigDecimal getValorDiaria() {
		return valorDiaria;
	}
	public void setValorDiaria(BigDecimal valorDiaria) {
		this.valorDiaria = valorDiaria;
	}
}