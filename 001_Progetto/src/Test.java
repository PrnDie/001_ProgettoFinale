import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.*;
import java.util.*;
import org.json.*;

/*import org.json.JSONValue;
import org.json.parser.ParseExtyception;78*/

public class Test {
	
	final static String COMMA_DELIMITER = ";";

	public static void main(String[] args) {

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
				InputStreamReader inR = new InputStreamReader( in );
				BufferedReader buf = new BufferedReader( inR );
			  
				   while ((line = buf.readLine()) != null)
				   {
					   data+= line;
					   System.out.println( line );
				   }
			   } finally {
				   in.close();
				   }
			 
			JSONObject obj = (org.json.JSONObject) json.parseWithException(data); 
			JSONObject objI = (JSONObject) (obj.get("result"));
			JSONArray objA = (JSONArray) (objI.get("resources"));
			
			for(Object o: objA){
			    if ( o instanceof JSONObject ) {
			        JSONObject o1 = (JSONObject)o; 
			        String format = (String)o1.get("format");
			        String urlD = (String)o1.get("url");
			        System.out.println(format + " | " + urlD);
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

	Employee e = new Employee("Mario", "Via Brecce Bianche", 65535, 1024 * 1024 - 2);
	/*
			try {
				FileOutputStream fileOut = new FileOutputStream("employee.ser");
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				out.writeObject(e);
				out.close();
				fileOut.close();
				System.out.printf("Serialized data is saved in employee.ser");
			} catch (IOException i) {
				i.printStackTrace();
			}
	*/
			Employee e1 = null;		
			try {
				FileInputStream fileIn = new FileInputStream("employee.ser");
				ObjectInputStream in = new ObjectInputStream(fileIn);
				e1 = (Employee) in.readObject();
				in.close();
				fileIn.close();
			} catch (IOException i) {
				i.printStackTrace();
				return;
			} catch (ClassNotFoundException c) {
				System.out.println("Employee class not found");
				c.printStackTrace();
				return;
			}

			List<List<String>> records = new ArrayList<>();
			Vector<Employee> v = new Vector<Employee>();
			try (BufferedReader br = new BufferedReader(new FileReader("book.csv"))) {
				String line;
				while ((line = br.readLine()) != null) {
					String[] values = line.split(COMMA_DELIMITER);
					System.out.println(values.length);
					records.add(Arrays.asList(values));
					v.add(new Employee(values[0], values[1], Integer.parseInt(values[2]), Integer.parseInt(values[3])));
				}
				br.close();
			} catch (IOException i) {
				i.printStackTrace();
				return;
			}
			for(Employee item: v) {		
				System.out.println(v.toString());
			}

			List<List<String>> records2 = new ArrayList<>();
			try (Scanner s = new Scanner(new File("book.csv"));) {
				while (s.hasNextLine()) {
					records2.add(getRecordFromLine(s.nextLine()));
				}
				s.close();
			} catch (IOException i) {
				i.printStackTrace();
				return;
			}
		}

		private static List<String> getRecordFromLine(String line) {
			List<String> values = new ArrayList<String>();
			try (Scanner rowScanner = new Scanner(line)) {
				rowScanner.useDelimiter(COMMA_DELIMITER);
				while (rowScanner.hasNext()) {
					values.add(rowScanner.next());
				}
			}
			return values;
		}

}

}

public static void download(String url, String fileName) throws Exception {
    try (InputStream in = URI.create(url).toURL().openStream()) {
        Files.copy(in, Paths.get(fileName));
    }
}
}