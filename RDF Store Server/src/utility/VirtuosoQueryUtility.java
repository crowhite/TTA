package utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.StringTokenizer;

import utility.Constant;
import view.ServerLogger;

import com.hp.hpl.jena.rdf.model.Model;

public class VirtuosoQueryUtility {
	public final static String INPUT_GRAPH = "<LearningAnalysisTest>";
	public final static String META_DATA_GRAPH = "<AnalysisMetaData>";
	private final static String VIRTUOSO_URL = Constant.SERVER_URL + ":8890/sparql";
	
	
	public static boolean insert(Model model) {
		StringWriter out = new StringWriter();
		model.write(out, "N-Triples");
		String RDFString = out.toString();
		
		String query = "INSERT { GRAPH "
						+ INPUT_GRAPH
						+ " { "
						+ RDFString
						+ " } }";
		
		query = query.replace("\n", "");
		
		System.out.println(query);
		
		if(send(query) != null)
			return true;
		
		return false;
	}
	
	public static String select(String query) {
		return send("PREFIX : <" + Constant.PREFIX + ">" + query);
	}
	
	public static void drop() {
		send("DROP SILENT GRAPH <AnalysisResult>");
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
	
	public static String send(String query) {
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
				
//				System.out.println(result);
				ServerLogger.printLog("Virtuoso Response : \n");
				ServerLogger.printLog(result + "\n\n");
				
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
