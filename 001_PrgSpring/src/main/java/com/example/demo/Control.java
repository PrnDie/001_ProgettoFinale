package com.example.demo;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;


/** Classe utilizzare per la gestione delle richieste tramite browser
 * @author Diego Pranzetti
 * @author Matteo Vitellozzi
 *
 */
@RestController
public class Control
{
	@Autowired
	private Serv serv;

	/** Metodo che in avvio stampa una legenda con le varie per interagire
	 * 
	 * @return Legenda con cui si può interagire 
	 */
	@RequestMapping("/")
	public String Start()
	{
		return "Benvenuto, è possibile interagire con il database utilizzando i seguenti comandi:"
				+ "\n- /media -------------> Numero delle ripetizioni elencate per potenza;"
				+ "\n- /metadati ----------> Nome e tipo degli attributi;"
				+ "\n- /dati --------------> Stampa di tutti i dati contenuti;"
				+ "\n- /dati?filter=<_100 -> Stampa filtrata, è possibile utilizzare il segno '<' o '>' seguito da '_' e il valore da prendere in considerazione";
	}
	
	
	/** Metodo che stampa il numero di ripetizioni elencate per potenza
	 * 
	 * @return Numero di ripetizioni elencate per potenza
	 * @throws Exception
	 */
	@GetMapping("/media")
	public String Prova() throws Exception
	{
		return serv.media();
	}
	
	

	/** Metodo che stampa tutti i dati presenti nel file con la possibilità di effettuare una filtrazione dei tali
	 *
	 * @param filter
	 * @return null
	 */
	@GetMapping("/dati")  
    public ArrayList<Ripetitore> Dati(@RequestParam(value="filter", defaultValue="Std") String filter)
	{
        try {
			return serv.Dati(filter);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return null;
    }
	
	
	/** Metodo che stampa il tipo dei dati in formato json
	 * 
	 * @return json sotto forma di json stampa il tipo dei dati analizzati 
	 * @throws Exception
	 */
	@GetMapping("/metadati")
	public String MetaDati() throws Exception
	{		
		ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
		mapper.toString();
		String json = mapper.writeValueAsString(serv.MetaDati());
		return json;   
	}
}