package query;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.StringTokenizer;

//import java.io.StringWriter;

//import com.hp.hpl.jena.rdf.model.Model;

public class VirtuosoQueryUtility {
	public final static String GRAPH = "<LearningAnalysisTest>";
	private final static String VIRTUOSO_URL = Constant.SERVER_URL + ":8890/sparql";
	
//	public static boolean insert(Model model) {
//		StringWriter out = new StringWriter();
//		model.write(out, "N-Triples");
//		String RDFString = out.toString();
//		
//		String query = "INSERT { GRAPH "
//						+ GRAPH
//						+ " { "
//						+ RDFString
//						+ " } }";
//		
//		query = query.replace("\n", "");
//		
//		if(send(query) != null)
//			return true;
//		
//		return false;
//	}
	
	public static String selectRMetaData(String type){
		return send("SELECT ?s ?o WHERE {GRAPH <AnalysisMetaData> {?s <" +  type + "> ?o}}");
	}
	
	public static String select(String query) {
		return send("PREFIX : <" + Constant.PREFIX + ">" + query);
	}
	
	public static void drop(String graph) {
		send("DROP SILENT GRAPH <" + graph + ">");
	}
	
	public static int queryResultToInt(String queryResult) {
		String str;
		int result;
		
		str = parseQueryResult(queryResult);
		
		if(str == null)
			result = 0;
		else
			result = Integer.parseInt(str);
		
		return result + 1;
	}
	
	public static String parseQueryResult(String queryResult) {
		String result = null;
		StringTokenizer tokenizer = new StringTokenizer(queryResult, "\n");

		if (tokenizer.countTokens() == 2) {
			tokenizer.nextToken();
			result = tokenizer.nextToken().replace("\"", "");
		}
		
		return result;
	}
	
	private static String send(String query) {
		String result = null;
		try {
			URL obj;
			obj = new URL(VIRTUOSO_URL);
			HttpURLConnection con = (HttpURLConnection)obj.openConnection();
			con.setDoOutput(true);
			OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());
			
			String params = "query=" + query;
			params = params.replace(" ", "+");
			params = params + "&format=csv";
			
			writer.write(params);
			writer.flush();
			
			String line;
			
			if(con.getResponseCode() == 200) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
				result = "";

				while ((line = reader.readLine()) != null) {
					result += line + "\n";
				}

				System.out.println(result);
				
				reader.close();
			}
			
			writer.close();
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
