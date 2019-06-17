package com.example.demo;

import java.util.ArrayList;
import java.util.Arrays;
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

import com.example.demo.Ripetitore;

@Component
public class Serv
{
	public static Vector<Ripetitore> v1 = new Vector<Ripetitore>();
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
					insertdata("t1.csv", v1);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
	
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
		System.out.println("download effettuata");
	}
	
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
	
	public static void insertdata(String file, Vector v) throws Exception
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
		System.out.println("parsing effettuata");
	}
	
	public String prova() throws Exception
	{
		return v1.get(50).toString();
	}
	
	public String media( throws Exception) {
}
