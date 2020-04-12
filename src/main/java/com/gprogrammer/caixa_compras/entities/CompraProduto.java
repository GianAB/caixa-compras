package com.gprogrammer.caixa_compras.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_compra_produto")
public class CompraProduto implements Serializable {
	private static final long serialVersionUID = -1731499304645382382L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;
	
	@ManyToOne
	@JoinColumn(name = "id_compra")
	private Compra compra;
	
	@ManyToOne
	@JoinColumn(name = "id_produto")
	private Produto produto;
	
	public CompraProduto() {
	}
	
	public CompraProduto(Compra compra, Produto produto) {
		this.compra = compra;
		this.produto = produto;
	}
	
	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public Compra getCompra() {
		return compra;
	}
	public void setCompra(Compra compra) {
		this.compra = compra;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
}
