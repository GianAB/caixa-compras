package com.gprogrammer.caixa_compras.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="tb_compra")
public class Compra implements Serializable{
	private static final long serialVersionUID = -7161298510178741153L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String cliente;
	
	@ManyToMany
	@JoinTable(name="tb_compra_produto", joinColumns = @JoinColumn(name="id_compra"), inverseJoinColumns = @JoinColumn(name="id_produto"))
	private List<Produto> produtos = new ArrayList<>();
	
	public Compra() {
	}
	
	public Compra(Integer id, String cliente) {
		this.id = id;
		this.cliente = cliente;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public List<Produto> getProdutos() {
		return produtos;
	}
	
	@Override
	public String toString() {
		return "Cliente: " + cliente + id;
	}
}
