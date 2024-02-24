package com.sample;

public class Diagnostico {
	private String nomePaciente;
	private String idPaciente;
	private String designacao;
	
	public Diagnostico(String idPaciente, String nomePaciente, String designacao) {
		super();
		this.nomePaciente = nomePaciente;
		this.idPaciente = idPaciente;
		this.designacao = designacao;
	}

	public String getNomePaciente() {
		return nomePaciente;
	}

	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}

	public String getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(String idPaciente) {
		this.idPaciente = idPaciente;
	}

	public String getDesignacao() {
		return designacao;
	}

	public void setDesignacao(String designacao) {
		this.designacao = designacao;
	}
}