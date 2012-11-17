package gmin332.app.utils;

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

public class JenaUtils {
	private static final Logger logger = Logger.getLogger(JenaUtils.class);

	public static Model importModel(String uri_ontology) {
		logger.debug("Running GMIN332-1");
		logger.debug("URI ontology : " + uri_ontology);

		Model model = ModelFactory.createDefaultModel();

		InputStream in = FileManager.get().open(uri_ontology);
		if (in == null) {
			throw new IllegalArgumentException("Fichier: " + uri_ontology
					+ " non trouvé");
		}

		return model;
	}
}
