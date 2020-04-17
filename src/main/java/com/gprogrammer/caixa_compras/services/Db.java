package com.gprogrammer.caixa_compras.services;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.gprogrammer.caixa_compras.entities.Compra;
import com.gprogrammer.caixa_compras.entities.Produto;
import com.gprogrammer.caixa_compras.exceptions.Exceptions;

public class Db {
	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Caixa-PU");
	static EntityManager em = emf.createEntityManager();
	
	public static Produto consultarProduto(Integer id) {
		String jpql = "select p from Produto p where id = " + id;
		TypedQuery<Produto> tq = em.createQuery(jpql, Produto.class);
		Produto produto = tq.getSingleResult();
		return produto;
	}
	
	public static Produto consultarProduto(String nome) {
		Produto produto = null;
		String jpql = "select p from Produto p where nome = '" + nome + "'";
		TypedQuery<Produto> tq = em.createQuery(jpql, Produto.class);
		try {
			produto = tq.getSingleResult();
		} catch (NoResultException e) {
			throw new Exceptions("Produto não encontrado: " + e.getMessage());
		}
		return produto;
	}
	
	public static Produto consultarEstoque(String nomeProduto, Integer qtde) {
		Produto produto = null;
		String jpql = "select p from Produto p where nome = '" + nomeProduto + "' AND qtde_estoque >= '" + qtde + "'";
		TypedQuery<Produto> tq = em.createQuery(jpql, Produto.class);
		try {
			produto = tq.getSingleResult();
		} catch (NoResultException e) {
			throw new Exceptions("Quantidade requerida maior do que a quantidade em estoque: " + e.getMessage());
		}
		
		return produto;
	}
	
	public static Compra consultarCompra(String nome) {
		String jpql = "select c from Compra c where cliente = '" + nome + "'";
		TypedQuery<Compra> tq = em.createQuery(jpql, Compra.class);
		Compra compra= tq.getSingleResult();
		return compra;
	}
	
	public static void adicionar(Produto produto) {
		em.getTransaction().begin();
		em.persist(produto);
		em.getTransaction().commit();
	}
}
