
public class Ripetitore {
	// Attributes
	private String gestore;
	private String impianto;
	private String indirizzo;
	private String potenza;
	
	
	// ethods
	public Ripetitore(String gestore, String impianto, String indirizzo, String potenza) {
		super();
		this.gestore = gestore;
		this.impianto = impianto;
		this.indirizzo = indirizzo;
		this.potenza = potenza;
	}
	
	
	// Getters and Setters
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
