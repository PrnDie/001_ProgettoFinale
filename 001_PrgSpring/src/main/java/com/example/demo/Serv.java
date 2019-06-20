package com.example.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;
import java.lang.reflect.Field;


import com.example.demo.Ripetitore;

/** Classe utilizzare per la dichiarazione del vettore su cui poi vado ad interagire con le varie richieste. Tramite l'url poi
 * effettua la lettura e il parsing tramite i metodi readdata e insertdata
 * @author Diego Pranzetti
 * @author Matteo Vitellozzi
 */
@Component
public class Serv
{
	public static Vector<Ripetitore> v = new Vector<Ripetitore>();
	final static String COMMA_DELIMITER = ";";

	static {
		
			String url = "https://www.dati.gov.it/api/3/action/package_show?id=96404f82-975e-490e-89e5-966181f72b4c";
			
			try {
					readdata(url);
				} catch (IOException | ParseException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			
			try {
					insertdata("t1.csv");
				} catch (Exception e) {
					e.printStackTrace();
				}	
			}
	
	
	/** Metodo che effettua il download dei dati dall'URL passatogli, effettua un primo controllo di esistenza del file 
	 * e tramite il try scarica i dati aprende
	 * @param url
	 * @param fileName
	 * @throws Exception
	 */
	public static void download(String url, String fileName) throws Exception
	{
	    File f = new File(fileName);
	    /**
	     * Mi assicuro che il file esista
	     */
	    if (f.exists()) {
	    	f.delete();
	    }
	    
		try (InputStream in = URI.create(url).toURL().openStream()) {
			Files.copy(in, Paths.get(fileName));
		}
		System.out.println("Download effettuato");
	}
	
	/** Metodo che effettua la lettura dei dati dall'URL passatogli, crea due stringhe data e line le quali si occupano di leggere riga
	 *  per riga il file dell'url. Line, tramite il while controlla l'andata a capo del file di testo(quindi la fine della riga). Successivamente, tramite il for
	 *  vado a cercare nel file le parole "format","url","csv" da cui poi vado a scaricare i dati 
	 * @param url url dal quale aprire il file da leggere ed analizzare
	 * @throws Exception
	 */
	public static void readdata(String url) throws Exception
	{
		URLConnection openConnection = new URL(url).openConnection();
		openConnection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
		InputStream in = openConnection.getInputStream();
		String data = "";
		String line = "";
		try {
			InputStreamReader inR = new InputStreamReader(in);
			BufferedReader buf = new BufferedReader(inR);
			
				while ((line = buf.readLine()) != null) { 
					data+= line;
				}
			} finally {
				in.close();
				}
		 
		JSONObject obj = (JSONObject) JSONValue.parseWithException(data);
		JSONObject objI = (JSONObject) (obj.get("result"));
		JSONArray objA = (JSONArray) (objI.get("resources"));
		
		for(Object o: objA) {                 
		    if (o instanceof JSONObject) {
		        JSONObject o1 = (JSONObject)o; 
		        String format = (String)o1.get("format");
		        String urlD = (String)o1.get("url");
		        if(format.equals("csv"))
		        {
		        	download(urlD, "t1.csv");
		        }
		    }
		}
		System.out.println("Lettura effettuata");
	}
	
	public static void insertdata(String file) throws Exception
	{
		List<List<String>> records = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = br.readLine()) != null) { 
				String[] values = line.split(COMMA_DELIMITER); 
				records.add(Arrays.asList(values));
				v.add(new Ripetitore(values[0], values[1], values[2], values[3]));
			}
		br.close();
		} catch (IOException i) {
			i.printStackTrace();
			return;
		}
		System.out.println("Parsing effettuato");
	}
	
	public Vector<Ripetitore> dati() throws Exception
	{
		return v;
	}
	
	public Ripetitore dati(int i) throws Exception
	{
		return v.get(i);
	}
	
	
	/** Metodo per il calcolo della media, visualizzando tutte le "Potenze" di ogni elemento calcolo la frequenza con 
	 *  cui si ripetono, con una comparazione confronto ogni elemento con i 7 casi possibili.
	 * @return pot1,pot2,pot3,pot4,pot5,pot6, sommma la frequenza delle potenze analizzate e la sua somma 
	 * @throws Exception
	 */
	public String media()  throws Exception
	{
		int pot1=0,pot2=0,pot3=0,pot4=0,pot5=0,pot6=0,somma=0;
		System.out.println("Elenco Impianti di Telecomunicazione e Radiotelevisione nel Comune di Milano");
		
		String s0= " <= 7";
		String s1= "<= 7";
		String s2= "> 7 e <= 20";
		String s3= "> 7 e <= 21";
		String s4= "> 20 e <= 300";
		String s5= "> 300 e <= 1000";
		String s6= "> 1000";
	 
	    for (int i=1; i < v.size(); i++) {
		   if( (v.get(i).getpotenza().equalsIgnoreCase(s1)) || (v.get(i).getpotenza().equalsIgnoreCase(s0)) ){
			   pot1++;
		   }
		   else if( v.get(i).getpotenza().equalsIgnoreCase(s2) ) {
			   pot2++;
		   }
		   else if( v.get(i).getpotenza().equalsIgnoreCase(s3)) {
			   pot3++;
		   }
		   else if( v.get(i).getpotenza().equalsIgnoreCase(s4)) {
			   pot4++;
		   }
		   else if( v.get(i).getpotenza().equalsIgnoreCase(s5)) {
			   pot5++;
		   }
		   else if( v.get(i).getpotenza().equalsIgnoreCase(s6)) {
			   pot6++;
		   }
		   somma=pot1+pot2+pot3+pot4+pot5+pot6;
	   }
	    return ("\nPotenza <= 7: " + pot1 +"\nPotenza > 7 e <= 20: " + pot2 +"\nPotenza > 7 e <= 21: " + pot3+ "\nPotenza > 20 e <= 300: " + pot4 + "\nPotenza > 300 e <= 1000: " + pot5 + "\nPotenza > 1000: " + pot6+ "\n Totale elementi analalizzati:" + somma);
   }
	
	public Ripetitore rip(int i) throws Exception
	{
		return v.get(i);
	}
	
	
	/** Metodo che stampa sotto forma di json il tipo del dato preso in analisi. Creo un array che viene riempito
	 *  dai dati della classe Ripetitore. Attraverso il for che legge ognuno di essi vado a definire il tipo del dato
	 * @return m Elenco dei tipi di dati presi in analisi
	 */
	public Collection MetaDati()
	{
		List<MetaDati> m = new ArrayList<>();
		Field[] fields = Ripetitore.class.getDeclaredFields();
		
		for (Field o : fields)
		{
				MetaDati mm = new MetaDati();
				mm.setNome(o.getName());
				mm.setTipo(o.getType().getSimpleName());
				m.add (mm);		
		}
		return m;
	}	
}
