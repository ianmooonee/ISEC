package com.sample;

public class Pessoa {
	private String nome;
	private int idade;
	private String comidaPref;
	private int rendimento;
	
	public Pessoa(String nome, int idade,  String comidaPref, int rendimento) {
		super();
		this.nome = nome;
		this.idade = idade;
		this.comidaPref=comidaPref;
		this.rendimento=rendimento;
	}
	
	public int getRendimento() {
		return rendimento;
	}

	public void setRendimento(int rendimento) {
		this.rendimento = rendimento;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public int getIdade() {
		return idade;
	}
	
	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getComidaPref() {
		return comidaPref;
	}

	public void setComidaPref(String comidaPref) {
		this.comidaPref = comidaPref;
	}
}
