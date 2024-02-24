package com.sample;

public class Camara {
	Divisao divisao;
	private int id;
	private String zonaActividade;
	private boolean detecaoHumana;

	public Camara(Divisao divisao, int id, String zonaActividade, boolean detecaoHumana) {
		super();
		this.divisao = divisao;
		this.id = id;
		this.zonaActividade = zonaActividade;
		this.detecaoHumana = detecaoHumana;
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

	public String getZonaActividade() {
		return zonaActividade;
	}

	public void setZonaActividade(String zonaActividade) {
		this.zonaActividade = zonaActividade;
	}

	public boolean isDetecaoHumana() {
		return detecaoHumana;
	}

	public void setDetecaoHumana(boolean detecaoMovimento) {
		this.detecaoHumana = detecaoMovimento;
	}
}
