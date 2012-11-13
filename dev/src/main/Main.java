package main;

import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Selector;
import com.hp.hpl.jena.rdf.model.SimpleSelector;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.util.FileManager;

public class Main {
	private static final Logger logger = Logger.getLogger(Main.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String uri_ontology = "http://www.geonames.org/ontology/ontology_v2.0_Lite.rdf";

		logger.debug("Running GMIN332-1");
		logger.debug("URI ontology : " + uri_ontology);

		Model model = ModelFactory.createDefaultModel();

		InputStream in = FileManager.get().open(uri_ontology);
		if (in == null) {
			throw new IllegalArgumentException("Fichier: " + uri_ontology
					+ " non trouvé");
		}

		model.read(in, null);

		logger.debug("PREFIXES");
		for (Map.Entry<String, String> ns : model.getNsPrefixMap().entrySet()) {
			logger.debug("    " + ns.getKey() + ":\t" + ns.getValue());
		}

		model.write(System.out);
	}

}
