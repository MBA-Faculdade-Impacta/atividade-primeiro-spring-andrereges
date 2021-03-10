package br.com.impacta.lab.controller;

public class Produto {

	private int id;
	private String descricao;
	private Double valor;

	public Produto() {}

	public Produto(int Id, String descricao, Double valor) {
		this.id = id;
		this.descricao = descricao;
		this.valor = valor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}
}