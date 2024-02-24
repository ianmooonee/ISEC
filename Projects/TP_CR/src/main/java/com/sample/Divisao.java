package com.sample;

public class Divisao {
	private String nome;
	private int nivelSeguranca;
	private int tentativasAcesso;
	private int quantidadePessoas;
	private boolean autoridadeNotificada;
	private boolean portaTrancada;

	public Divisao(String nome, int nivelSeguranca) {
		super();
		this.nome = nome;
		this.nivelSeguranca = nivelSeguranca;
		this.tentativasAcesso=0;
		this.autoridadeNotificada=false;
		this.quantidadePessoas=0;
		portaTrancada=false;
	}

	public boolean isAutoridadeNotificada() {
		return autoridadeNotificada;
	}

	public void setAutoridadeNotificada(boolean autoridadeNotificada) {
		this.autoridadeNotificada = autoridadeNotificada;
	}

	public int getTentativasAcesso() {
		return tentativasAcesso;
	}

	public void setTentativasAcesso(int tentativasAcesso) {
		this.tentativasAcesso = tentativasAcesso;
	}

	public int getNivelSeguranca() {
		return nivelSeguranca;
	}

	public void setNivelSeguranca(int nivelSeguranca) {
		this.nivelSeguranca = nivelSeguranca;
	}

	public int getNivelAcesso() {
		return nivelSeguranca;
	}

	public void setNivelAcesso(int nivelSeguranca) {
		this.nivelSeguranca = nivelSeguranca;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getQuantidadePessoas() {
		return quantidadePessoas;
	}

	public void setQuantidadePessoas(int quantidadePessoas) {
		this.quantidadePessoas = quantidadePessoas;
	}

	public boolean isPortaTrancada() {
		return portaTrancada;
	}

	public void setPortaTrancada(boolean portaTrancada) {
		this.portaTrancada = portaTrancada;
	}
}
