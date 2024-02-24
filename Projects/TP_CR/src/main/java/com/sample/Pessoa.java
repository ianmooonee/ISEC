package com.sample;

public class Pessoa {
	private String nome;
	private String localizacao;
	private int id;
	private int nivelAcesso;
	private Divisao divisao;
	
	public Pessoa(String nome, int id, int nivelAcesso, Divisao divisao, String localizacao) {
		super();
		this.nome = nome;
		this.id = id;
		this.nivelAcesso = nivelAcesso;
		this.divisao = divisao;
		this.localizacao=localizacao;
	}
	
	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	public Divisao getDivisao() {
		return divisao;
	}

	public void setDivisao(Divisao divisao) {
		this.divisao = divisao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getNivelAcesso() {
		return nivelAcesso;
	}

	public void setNivelAcesso(int nivelAcesso) {
		this.nivelAcesso = nivelAcesso;
	}
}
