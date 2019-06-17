package com.example.demo;

/**
 * Considerata la semplicità della gerarchia di attributi presenti, abbiamo ritenuto opportuno creare una classe soltanto
 * che contiene come attributi le colonne del nostro database. *
 */
public class Ripetitore
{
	/**
	 * Attributi
	 * Tutti gli attributi sono di tipo string, anche potenza, perchè al suo interno troviamo dei simboli.
	 */
	private String gestore;
	private String impianto;
	private String indirizzo;
	private String potenza;
	
	/**
	 * Metodi
	 */
	public Ripetitore(String gestore, String impianto, String indirizzo, String potenza) {
		super();
		this.gestore = gestore;
		this.impianto = impianto;
		this.indirizzo = indirizzo;
		this.potenza = potenza;
	}
	
	/**
	 * Getter and Setter 
	 */
	public String getgestore() {
		return gestore;
	}

	public void setgestore(String gestore) {
		this.gestore = gestore;
	}

	public String getimpianto() {
		return impianto;
	}

	public void setimpianto(String impianto) {
		this.impianto = impianto;
	}

	public String getindirizzo() {
		return indirizzo;
	}

	public void setindirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getpotenza() {
		return potenza;
	}

	public void setpotenza(String potenza) {
		this.potenza = potenza;
	}
	
	@Override
	public String toString() {
		return gestore + ", " + impianto + ", " + indirizzo + ", " + potenza;
	}
}