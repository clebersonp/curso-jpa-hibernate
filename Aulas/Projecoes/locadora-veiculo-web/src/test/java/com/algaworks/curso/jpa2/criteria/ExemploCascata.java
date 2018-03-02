package com.algaworks.curso.jpa2.criteria;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.algaworks.curso.jpa2.modelo.Carro;
import com.algaworks.curso.jpa2.modelo.Categoria;
import com.algaworks.curso.jpa2.modelo.Fabricante;
import com.algaworks.curso.jpa2.modelo.ModeloCarro;

public class ExemploCascata {

	private static EntityManagerFactory factory;
	private EntityManager manager;

	@BeforeClass
	public static void init() {
		factory = Persistence.createEntityManagerFactory("locadoraVeiculoPU");
	}
	
	@Before
	public void setUp() {
		manager = factory.createEntityManager();
	}
	
	@After
	public void tearDown() {
		this.manager.close();
	}
	
	@Test
	public void exemploEntidadeTransiente() {
		Carro wiki = new Carro();
		wiki.setChassi("asdfasd54a65sdf");
		wiki.setCor("Preto");
		
		Fabricante janny = new Fabricante();
		janny.setNome("Jenny");
		
		ModeloCarro modelo = new ModeloCarro();
		modelo.setCategoria(Categoria.HATCH_COMPACTO);
		modelo.setDescricao("wiki");
		modelo.setFabricante(janny);
		
		wiki.setModelo(modelo);
		
		this.manager.getTransaction().begin();
		this.manager.persist(wiki);
		this.manager.getTransaction().commit();
	}
	
}
