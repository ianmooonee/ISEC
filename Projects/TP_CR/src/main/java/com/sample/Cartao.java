package com.sample;

public class Cartao {
	private boolean valido;
	private Pessoa portador;
	
	public Cartao(boolean valido, Pessoa portador) {
		super();
		this.valido = valido;
		this.portador = portador;
	}
	
	public boolean isValido() {
		return valido;
	}

	public void setValido(boolean valido) {
		this.valido = valido;
	}

	public Pessoa getPortador() {
		return portador;
	}

	public void setPortador(Pessoa portador) {
		this.portador = portador;
	}	
}
