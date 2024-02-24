package com.sample;

public class SensorVolumetrico {
	private Divisao divisao;
	private boolean ativo;
	private boolean detecaoMovimento;
	
	public SensorVolumetrico(Divisao divisao, boolean ativo, boolean detecaoMovimento) {
		super();
		this.divisao = divisao;
		this.ativo = ativo;
		this.detecaoMovimento=detecaoMovimento;
	}

	public Divisao getDivisao() {
		return divisao;
	}
	
	public void setDivisao(Divisao divisao) {
		this.divisao = divisao;
	}

	public boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public boolean getDetecaoMovimento() {
		return detecaoMovimento;
	}

	public void setDetecaoMovimento(boolean detecaoMovimento) {
		this.detecaoMovimento = detecaoMovimento;
	}
}
