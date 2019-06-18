package com.example.demo;

public class MetaDati
{
	private String nome, tipo;
	
	public MetaDati(String nome, String tipo)
	{
		this.nome = nome;
		this.tipo = tipo;
	}
	
	
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
	
	
	public String toString() {
		return "MetaDati [toString()=" + super.toString() + "]";
	}
}
