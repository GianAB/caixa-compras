package com.gprogrammer.caixa_compras;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.gprogrammer.caixa_compras.entities.Compra;
import com.gprogrammer.caixa_compras.entities.CompraProduto;
import com.gprogrammer.caixa_compras.entities.Produto;
public class App {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Character op = null;
		List<Produto> produtos = new ArrayList<>();
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Caixa-PU");
		EntityManager em = emf.createEntityManager();
		
		System.out.println("_________ COMPRA INICIADA _________");
		System.out.print("Nome do cliente: ");
		String cliente = sc.next(); sc.nextLine();
		
		Compra compra = new Compra(null, cliente);
		
		em.getTransaction().begin();
		em.persist(compra);
		em.getTransaction().commit();
		
		String jpqlCompra = "select c from Compra c where c.id = " + compra.getId() + ""; 
		TypedQuery<Compra> tpCompra = em.createQuery(jpqlCompra, Compra.class);
		compra = tpCompra.getSingleResult();
		
		System.out.println("\n_________ PRODUTO _________");
		do {
			System.out.print("Nome do produto: ");
			String nomeProduto = sc.next();sc.nextLine();
			
			String jpqlProduto = "Select p from Produto p where p.nome = '" + nomeProduto + "'";
			TypedQuery<Produto> tqProduto = em.createQuery(jpqlProduto, Produto.class);
			Produto produto = tqProduto.getSingleResult();
		
			System.out.print("Quantidade: ");
			int qtde = sc.nextInt();
			
			for(int i = 0; i < qtde; i++) {
				CompraProduto compProd = new CompraProduto(new Compra(compra.getId(), compra.getCliente()), new Produto(produto.getId(), produto.getNome(), produto.getValor()));
				em.getTransaction().begin();
				em.persist(compProd);
				em.getTransaction().commit();
				produtos.add(new Produto(produto.getId(), produto.getNome(), produto.getValor()));
			}
			
			System.out.print("Acrescentar mais algum produto?[s/n]: ");
			op = sc.next().charAt(0);sc.nextLine();
			
		} while (op == 's');
	
		System.out.println("\n_________ EXTRATO DA COMPRA _________");
		System.out.println("Cliente: " + compra.getCliente()+"\n");
		Double vlrTotal = 0.0;
		for(Produto p: produtos) {
			System.out.println(p.toString());
			vlrTotal += p.getValor();
		}
		System.out.println(String.format("\nTotal a pagar: R$ %.2f", vlrTotal));
		
		sc.close();
	}
}
