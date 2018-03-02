package com.algaworks.curso.jpa2.criteria;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.algaworks.curso.jpa2.modelo.Aluguel;
import com.algaworks.curso.jpa2.modelo.Carro;
import com.algaworks.curso.jpa2.modelo.Carro_;
import com.algaworks.curso.jpa2.modelo.Fabricante;
import com.algaworks.curso.jpa2.modelo.ModeloCarro;
import com.algaworks.curso.jpa2.modelo.ModeloCarro_;
import com.algaworks.curso.jpa2.modelo.PrecoCarro;

public class ExemplosCriteria {

	private static EntityManagerFactory factory;
	
	private EntityManager manager;
	
	@BeforeClass
	public static void init() {
		factory = Persistence.createEntityManagerFactory("locadoraVeiculoPU");
	}
	
	@Before
	public void setUp() {
		this.manager = factory.createEntityManager();
	}
	
	@After
	public void tearDown() {
		this.manager.close();
	}
	
	@Test
	public void projecoes() {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<String> criteriaQuery = builder.createQuery(String.class);
		
		Root<Carro> carro = criteriaQuery.from(Carro.class);
		criteriaQuery.select(carro.<String>get("placa"));
		
		TypedQuery<String> query = manager.createQuery(criteriaQuery);
		List<String> placas = query.getResultList();
		
		for (String placa : placas) {
			System.out.println(placa);
		}
	}  
	
	@Test
	public void funcoesDeAgregacao() {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<BigDecimal> criteriaQuery = builder.createQuery(BigDecimal.class);
		
		Root<Aluguel> a = criteriaQuery.from(Aluguel.class);
		
		// JPQL -> select sum(a.valorTotal) from Aluguel a
		criteriaQuery.select(builder.sum(a.<BigDecimal>get("valorTotal")));
		
		TypedQuery<BigDecimal> query = manager.createQuery(criteriaQuery);
		
		BigDecimal valorTotal = query.getSingleResult();
		
		System.out.println("O valor total é: " + valorTotal);
		
	}
	
	@Test
	public void resultadoComplexo() {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Object[]> criteriaQuery = builder.createQuery(Object[].class);
		Root<Carro> from = criteriaQuery.from(Carro.class);
		
		criteriaQuery.multiselect(from.get("placa"), from.get("valorDiaria"));
		
		List<Object[]> resultado = manager.createQuery(criteriaQuery).getResultList();
		
		for (Object[] carro : resultado) {
			System.out.println("resultadoComplexo(): " + carro[0] + " - " + carro[1]);
		}
	}
	
	@Test
	public void resultadoTupla() {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Tuple> tupleQuery = builder.createTupleQuery();
		Root<Carro> from = tupleQuery.from(Carro.class);
		
		tupleQuery.multiselect(from.get("placa").alias("placaDoCarro"), from.get("valorDiaria").alias("valorDaDiariaDoCarro"));
		
		TypedQuery<Tuple> createQuery = manager.createQuery(tupleQuery);
		
		List<Tuple> resultado = createQuery.getResultList();
		
		for (Tuple carro : resultado) {
			System.out.println("resultadoTupla(): " + carro.get("placaDoCarro") + " - " + carro.get("valorDaDiariaDoCarro"));
		}
	}
	
	@Test
	public void resultadoConstroiObjeto() {
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<PrecoCarro> criteriaQuery = builder.createQuery(PrecoCarro.class);
		Root<Carro> root = criteriaQuery.from(Carro.class);
		
		CriteriaQuery<PrecoCarro> multiselect = criteriaQuery.select(builder.construct(PrecoCarro.class, root.get("placa"), root.get("valorDiaria")));
		
		TypedQuery<PrecoCarro> createQuery = manager.createQuery(multiselect);
		
		List<PrecoCarro> resultado = createQuery.getResultList();
		
		for (PrecoCarro carro : resultado) {
			System.out.println("resultadoConstroiObjeto(): " + carro.getPlaca() + " - " + carro.getValorDiaria());
		}
		
	}
	
	@Test
	public void resultadoComUpperCase() {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Carro> criteriaQuery = builder.createQuery(Carro.class);
		Root<Carro> from = criteriaQuery.from(Carro.class);
		
		Predicate predicate = builder.equal(builder.upper(from.<String>get("cor")), "prata".toUpperCase());
		
		criteriaQuery.where(predicate);
		
		TypedQuery<Carro> typedQuery = manager.createQuery(criteriaQuery);
		
		List<Carro> resultado = typedQuery.getResultList();
		
		for (Carro carro : resultado) {
			System.out.println("resultadoComUpperCase(): " + carro.getPlaca() + " - " + carro.getCor());
		}
	}
	
	@Test
	public void exemploOrdenacao() {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Carro> criteriaQuery = builder.createQuery(Carro.class);
		Root<Carro> from = criteriaQuery.from(Carro.class);
		
		Order order = builder.desc(from.get("valorDiaria"));
		
		criteriaQuery.select(from).orderBy(order);
		
		TypedQuery<Carro> typedQuery = manager.createQuery(criteriaQuery);
		
		List<Carro> carros = typedQuery.getResultList();
		
		for (Carro c : carros) {
			System.out.println("exemploOrdernacao(): " + c.getPlaca() + " - " + c.getValorDiaria());
		}
	}
	
	@Test
	public void exemploDeJoin() {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Carro> criteriaQuery = builder.createQuery(Carro.class);
		Root<Carro> from = criteriaQuery.from(Carro.class);
		
		Join<Carro, ModeloCarro> joinModeloCarro = from.join("modelo");
		
		criteriaQuery.select(from)
						.where(builder.equal(joinModeloCarro.get("codigo"), from.get("modelo")));
		
		List<Carro> carros = manager.createQuery(criteriaQuery).getResultList();
		
		for (Carro c : carros) {
			System.out.println("exemploDeJoin(): " + c.getPlaca());
		}
	}
	
	@Test
	public void exemploDeFetch() {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Carro> criteriaQuery = builder.createQuery(Carro.class);
		Root<Carro> from = criteriaQuery.from(Carro.class);
		
		Fetch<Carro, ModeloCarro> fetchCarroModeloCarro = from.fetch("modelo", JoinType.INNER);
		Fetch<ModeloCarro, Fabricante> fetchModeCarroFabricante = fetchCarroModeloCarro.fetch("fabricante", JoinType.INNER);
		
		criteriaQuery.select(from);
		
		List<Carro> carros = manager.createQuery(criteriaQuery).getResultList();
		
		for (Carro c : carros) {
			System.out.println("exemploDeFetch(): " + c.getPlaca() 
						+ " - " + c.getModelo().getDescricao() 
						+ " - " + c.getModelo().getFabricante().getNome());
		}
	}
	
	@Test
	public void exemploQueryComAVG() {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Double> criteriaQuery = builder.createQuery(Double.class);
		Root<Carro> from = criteriaQuery.from(Carro.class);

		criteriaQuery.select(builder.avg(from.<Double>get("valorDiaria")));
		
		TypedQuery<Double> typedQuery = manager.createQuery(criteriaQuery);
		Double media = typedQuery.getSingleResult();
		
		System.out.println("A média é: " + media);
		
	}
	
	@Test
	public void exemploQueryComSubQuery() {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Carro> criteriaQuery = builder.createQuery(Carro.class);
		Subquery<Double> subquery = criteriaQuery.subquery(Double.class);

		Root<Carro> from = criteriaQuery.from(Carro.class);
		Root<Carro> carroSub = subquery.from(Carro.class);
		
		subquery.select(builder.avg(carroSub.<Double>get("valorDiaria")));
		
		criteriaQuery.select(from).where(builder.greaterThanOrEqualTo(from.<Double>get("valorDiaria"), subquery));
		
		TypedQuery<Carro> typedQuery = manager.createQuery(criteriaQuery);
		List<Carro> carros = typedQuery.getResultList();
		
		for (Carro c : carros) {
			System.out.println("exemploQueryComSubQuery(): " + c.getValorDiaria());
		}
	}
	
	@Test
	public void exemploDeHibernateJPAMetaModel() {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Carro> criteriaQuery = builder.createQuery(Carro.class);
		Root<Carro> root = criteriaQuery.from(Carro.class);
		
		Join<Carro, ModeloCarro> modelo = root.join(Carro_.modelo, JoinType.INNER);
		
		criteriaQuery.select(root).where(builder.equal(modelo.get(ModeloCarro_.descricao), "Onix")).distinct(true);
		
		List<Carro> carros = manager.createQuery(criteriaQuery).getResultList();
		
		for (Carro carro : carros) {
			System.out.println(carro.getPlaca() + " - " + carro.getModelo().getDescricao());
		}
		
	}
}

































