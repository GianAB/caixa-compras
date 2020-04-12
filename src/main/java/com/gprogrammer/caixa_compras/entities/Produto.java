package com.gprogrammer.caixa_compras.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="tb_produto")
public class Produto implements Serializable{
	private static final long serialVersionUID = 7371690340861315352L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private Double valor;
	
	@ManyToMany(mappedBy = "produtos")
	private List<Compra> compras = new ArrayList<>();
	
	public Produto() {
	}
	
	public Produto(Integer id, String nome, Double valor) {
		this.id = id;
		this.nome = nome;
		this.valor = valor;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	public List<Compra> getCompras() {
		return compras;
	}
	
	@Override
	public String toString() {
		return "Nome: " + nome + String.format(" | valor unitário: R$ %.2f", valor);
	}
}
