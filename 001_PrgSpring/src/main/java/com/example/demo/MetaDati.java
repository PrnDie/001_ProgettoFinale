package com.example.demo;

/** Classe utilizzata per la definizione di metadato.
 */

public class MetaDati
{
	private String nome, tipo, source;
	
	public MetaDati() {	}
		
	public String getNome() {
		return nome;
	}
		
	public void setNome(String nome) {
		this.nome = nome;
	}
		
	public String getTipo() {
		return tipo;
	}
		
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String toString() {
		return "MetaDati [toString()=" + super.toString() + "]";
	}
}
