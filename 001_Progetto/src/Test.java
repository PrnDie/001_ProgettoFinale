import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

public class Test
{
	final static String COMMA_DELIMITER = ";";

	public static void main(String[] args)
	{
		/**
		 * Link dataaset 
		 */
		String url = "https://www.dati.gov.it/api/3/action/package_show?id=96404f82-975e-490e-89e5-966181f72b4c";
		
		if(args.length == 1)
			url = args[0];
		
		try {
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
			        if(format.equals("csv")) {
			        	download(urlD, "t1.csv");
			        }
			    }
			}
			
				System.out.println( "OK" );
			} catch (IOException | ParseException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

		
		List<List<String>> records = new ArrayList<>();
		Vector<Ripetitore> v = new Vector<Ripetitore>();
		try (BufferedReader br = new BufferedReader(new FileReader("t1.csv"))) {
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
	}
}