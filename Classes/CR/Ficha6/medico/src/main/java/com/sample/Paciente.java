package com.sample;

public class Paciente {
	private String nome;
	private String id;
	private int idade;
	private String diagnostico;
	
	public Paciente(String id, String nome, int idade, String diagnostico) {
		super();
		this.id = id;
		this.nome = nome;
		this.idade = idade;
		this.diagnostico = diagnostico;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}
}
