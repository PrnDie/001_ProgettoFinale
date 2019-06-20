package com.example.demo;

/** Classe utilizzata per la dichiarazione degli attributi.
 * Considerata la semplicità della gerarchia di attributi presenti, abbiamo ritenuto opportuno creare una classe soltanto
 * che contiene come attributi le colonne del nostro database. *
 */
public class Ripetitore
{
	/**
	 * Attributes
	 * Tutti gli attributi sono di tipo string, anche potenza, perchè al suo interno troviamo dei simboli.
	 */
	private String gestore;
	private String impianto;
	private String indirizzo;
	private String potenza;
	
	/**
	 * Methods
	 */
	public Ripetitore(String gestore, String impianto, String indirizzo, String potenza) {
		this.gestore = gestore;
		this.impianto = impianto;
		this.indirizzo = indirizzo;
		this.potenza = potenza;
	}
	
	public Ripetitore()
	{
	}

	/**
	 * Getters and Setters 
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
		return "Ripetitore [Gestore=" + gestore + ", Impianto=" + impianto + ", Indirizzo="
				+ indirizzo + ", Potenza=" + potenza + "]";
	}
}