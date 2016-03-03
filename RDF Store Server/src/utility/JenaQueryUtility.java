package utility;

import java.util.ArrayList;
import java.util.List;

import utility.Constant;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.update.UpdateAction;
import com.hp.hpl.jena.update.UpdateFactory;
import com.hp.hpl.jena.update.UpdateRequest;


public class JenaQueryUtility {
	public static List<String> runSelectQuery(Model model, String queryString, String var) {
		List<String> result = new ArrayList<String>();
		Query query = QueryFactory.create("PREFIX : <" + Constant.PREFIX + ">" + queryString);

		QueryExecution qexec = QueryExecutionFactory.create(query, model);
		ResultSet results = qexec.execSelect();

		while (results.hasNext()) {
			QuerySolution soln = results.nextSolution();

			result.add(soln.get(var).toString());
		}

		return result;
	}
	
//	public static RDFList runSelectQuery(Model model, String queryString) {
//		RDFList result = new RDFList();
//		Query query = QueryFactory.create(queryString);
//
//		try (QueryExecution qexec = QueryExecutionFactory.create(query, model)) {
//			ResultSet results = qexec.execSelect();
//
//			result.addPrefix(model.getNsPrefixMap());
//			while (results.hasNext()) {
//				QuerySolution soln = results.nextSolution();
//				Literal s = soln.getLiteral("s");
//				Literal p = soln.getLiteral("p");
//				Literal o = soln.getLiteral("o");
//
//				result.addRDF((new RDF(s.toString(), p.toString(), o.toString())));
//			}
//		}
//
//		return result;
//	}
	
	public static void runDeleteQuery(Model model, String queryString) {
		UpdateRequest request = UpdateFactory.create();
		request.add("PREFIX : <" + Constant.PREFIX + ">" + queryString);
		UpdateAction.execute(request, model);
	}
	
	public static boolean runAskQuery(Model model, String queryString) {
		Query query = QueryFactory.create("PREFIX : <" + Constant.PREFIX + ">" + queryString);
		QueryExecution qexec = QueryExecutionFactory.create(query, model);
		ResultSet results = qexec.execSelect();
		
		boolean result = false;

		if (results.hasNext()) {
			result = true;
		}

		return result;
	}
	
	public static int runCountQuery(Model model, String queryString) {
		int result = 0;
		Query query = QueryFactory.create("PREFIX : <" + Constant.PREFIX + ">" + queryString);

		try (QueryExecution qexec = QueryExecutionFactory.create(query, model)) {
			ResultSet results = qexec.execSelect();

			while (results.hasNext()) {
				results.nextSolution();
				result++;
			}
		}

		return result;
	}
}
