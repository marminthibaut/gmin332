package gmin332.app.utils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.hp.hpl.jena.graph.Graph;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.ontology.ProfileRegistry;
import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.tdb.store.Hash;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import com.hp.hpl.jena.vocabulary.OWL;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.vocabulary.RDFS;

public class JenaUtils {
	private static final Logger logger = Logger.getLogger(JenaUtils.class);

	public static OntModel importModel(String uri_ontology) {
		logger.debug("Running GMIN332-1");
		logger.debug("URI ontology : " + uri_ontology);

		OntModel model = ModelFactory.createOntologyModel();

		model.read(uri_ontology);
		if (model.isEmpty()) {
			throw new IllegalArgumentException("Fichier: " + uri_ontology
					+ " non trouvé ou vide");
		}

		model.setNsPrefix("owl", OWL.getURI());
		model.setNsPrefix("rdfs", RDFS.getURI());
		model.setNsPrefix("rdf", RDF.getURI());

		return model;
	}

	public static String modelToJson(OntModel model) {
		int i = 0;

		ExtendedIterator<OntClass> classes = model.listHierarchyRootClasses();
		// ExtendedIterator<OntClass> classes = model.listClasses();

		String json = "{\"id\":\"root\", \"name\":\"Thing\", \"data\":{}, \"children\":[";
		int no = 0;
		while (classes.hasNext()) {
			++i;
			OntClass c = (OntClass) classes.next();
			if (c.isClass()) {
				json += JenaUtils.printNode(c, no);
				++no;
			}
		}
		json += "]}";

		System.out.println(json);

		System.out.println(i + " root classes");

		// return
		// "{id:\"node02\", name:\"NoeudRacine\", data:{\"$color\": \"#FF0000\"}, children:[{id:\"node13\", name:\"WikipediaDocument\", data:{}, children:[{id:\"node24\", name:\"2.4\", data:{}, children:[{id:\"node35\", name:\"3.5\", data:{}, children:[{id:\"node46\", name:\"4.6\", data:{}, children:[{}]}]}, {id:\"node37\", name:\"3.7\", data:{}, children:[{id:\"node48\", name:\"4.8\", data:{}, children:[]}, {id:\"node49\", name:\"4.9\", data:{}, children:[]}, {id:\"node410\", name:\"4.10\", data:{}, children:[]}, {id:\"node411\", name:\"4.11\", data:{}, children:[]}]}, {id:\"node312\", name:\"3.12\", data:{}, children:[{id:\"node413\", name:\"4.13\", data:{}, children:[]}]}, {id:\"node314\", name:\"3.14\", data:{}, children:[{id:\"node415\", name:\"4.15\", data:{}, children:[]}, {id:\"node416\", name:\"4.16\", data:{}, children:[]}, {id:\"node417\", name:\"4.17\", data:{}, children:[]}, {id:\"node418\", name:\"4.18\", data:{}, children:[]}]}, {id:\"node319\", name:\"3.19\", data:{}, children:[{id:\"node420\", name:\"4.20\", data:{}, children:[]}, {id:\"node421\", name:\"4.21\", data:{}, children:[]}]}]}, {id:\"node222\", name:\"2.22\", data:{}, children:[{id:\"node323\", name:\"3.23\", data:{}, children:[{id:\"node424\", name:\"4.24\", data:{}, children:[]}]}]}]}, {id:\"node125\", name:\"1.25\", data:{}, children:[{id:\"node226\", name:\"2.26\", data:{}, children:[{id:\"node327\", name:\"3.27\", data:{}, children:[{id:\"node428\", name:\"4.28\", data:{}, children:[]}, {id:\"node429\", name:\"4.29\", data:{}, children:[]}]}, {id:\"node330\", name:\"3.30\", data:{}, children:[{id:\"node431\", name:\"4.31\", data:{}, children:[]}]}, {id:\"node332\", name:\"3.32\", data:{}, children:[{id:\"node433\", name:\"4.33\", data:{}, children:[]}, {id:\"node434\", name:\"4.34\", data:{}, children:[]}, {id:\"node435\", name:\"4.35\", data:{}, children:[]}, {id:\"node436\", name:\"4.36\", data:{}, children:[]}]}]}, {id:\"node237\", name:\"2.37\", data:{}, children:[{id:\"node338\", name:\"3.38\", data:{}, children:[{id:\"node439\", name:\"4.39\", data:{}, children:[]}, {id:\"node440\", name:\"4.40\", data:{}, children:[]}, {id:\"node441\", name:\"4.41\", data:{}, children:[]}]}, {id:\"node342\", name:\"3.42\", data:{}, children:[{id:\"node443\", name:\"4.43\", data:{}, children:[]}]}, {id:\"node344\", name:\"3.44\", data:{}, children:[{id:\"node445\", name:\"4.45\", data:{}, children:[]}, {id:\"node446\", name:\"4.46\", data:{}, children:[]}, {id:\"node447\", name:\"4.47\", data:{}, children:[]}]}, {id:\"node348\", name:\"3.48\", data:{}, children:[{id:\"node449\", name:\"4.49\", data:{}, children:[]}, {id:\"node450\", name:\"4.50\", data:{}, children:[]}, {id:\"node451\", name:\"4.51\", data:{}, children:[]}, {id:\"node452\", name:\"4.52\", data:{}, children:[]}, {id:\"node453\", name:\"4.53\", data:{}, children:[]}]}, {id:\"node354\", name:\"3.54\", data:{}, children:[{id:\"node455\", name:\"4.55\", data:{}, children:[]}, {id:\"node456\", name:\"4.56\", data:{}, children:[]}, {id:\"node457\", name:\"4.57\", data:{}, children:[]}]}]}, {id:\"node258\", name:\"2.58\", data:{}, children:[{id:\"node359\", name:\"3.59\", data:{}, children:[{id:\"node460\", name:\"4.60\", data:{}, children:[]}, {id:\"node461\", name:\"4.61\", data:{}, children:[]}, {id:\"node462\", name:\"4.62\", data:{}, children:[]}, {id:\"node463\", name:\"4.63\", data:{}, children:[]}, {id:\"node464\", name:\"4.64\", data:{}, children:[]}]}]}]}, {id:\"node165\", name:\"1.65\", data:{}, children:[{id:\"node266\", name:\"2.66\", data:{}, children:[{id:\"node367\", name:\"3.67\", data:{}, children:[{id:\"node468\", name:\"4.68\", data:{}, children:[]}, {id:\"node469\", name:\"4.69\", data:{}, children:[]}, {id:\"node470\", name:\"4.70\", data:{}, children:[]}, {id:\"node471\", name:\"4.71\", data:{}, children:[]}]}, {id:\"node372\", name:\"3.72\", data:{}, children:[{id:\"node473\", name:\"4.73\", data:{}, children:[]}, {id:\"node474\", name:\"4.74\", data:{}, children:[]}, {id:\"node475\", name:\"4.75\", data:{}, children:[]}, {id:\"node476\", name:\"4.76\", data:{}, children:[]}]}, {id:\"node377\", name:\"3.77\", data:{}, children:[{id:\"node478\", name:\"4.78\", data:{}, children:[]}, {id:\"node479\", name:\"4.79\", data:{}, children:[]}]}, {id:\"node380\", name:\"3.80\", data:{}, children:[{id:\"node481\", name:\"4.81\", data:{}, children:[]}, {id:\"node482\", name:\"4.82\", data:{}, children:[]}]}]}, {id:\"node283\", name:\"2.83\", data:{}, children:[{id:\"node384\", name:\"3.84\", data:{}, children:[{id:\"node485\", name:\"4.85\", data:{}, children:[]}]}, {id:\"node386\", name:\"3.86\", data:{}, children:[{id:\"node487\", name:\"4.87\", data:{}, children:[]}, {id:\"node488\", name:\"4.88\", data:{}, children:[]}, {id:\"node489\", name:\"4.89\", data:{}, children:[]}, {id:\"node490\", name:\"4.90\", data:{}, children:[]}, {id:\"node491\", name:\"4.91\", data:{}, children:[]}]}, {id:\"node392\", name:\"3.92\", data:{}, children:[{id:\"node493\", name:\"4.93\", data:{}, children:[]}, {id:\"node494\", name:\"4.94\", data:{}, children:[]}, {id:\"node495\", name:\"4.95\", data:{}, children:[]}, {id:\"node496\", name:\"4.96\", data:{}, children:[]}]}, {id:\"node397\", name:\"3.97\", data:{}, children:[{id:\"node498\", name:\"4.98\", data:{}, children:[]}]}, {id:\"node399\", name:\"3.99\", data:{}, children:[{id:\"node4100\", name:\"4.100\", data:{}, children:[]}, {id:\"node4101\", name:\"4.101\", data:{}, children:[]}, {id:\"node4102\", name:\"4.102\", data:{}, children:[]}, {id:\"node4103\", name:\"4.103\", data:{}, children:[]}]}]}, {id:\"node2104\", name:\"2.104\", data:{}, children:[{id:\"node3105\", name:\"3.105\", data:{}, children:[{id:\"node4106\", name:\"4.106\", data:{}, children:[]}, {id:\"node4107\", name:\"4.107\", data:{}, children:[]}, {id:\"node4108\", name:\"4.108\", data:{}, children:[]}]}]}, {id:\"node2109\", name:\"2.109\", data:{}, children:[{id:\"node3110\", name:\"3.110\", data:{}, children:[{id:\"node4111\", name:\"4.111\", data:{}, children:[]}, {id:\"node4112\", name:\"4.112\", data:{}, children:[]}]}, {id:\"node3113\", name:\"3.113\", data:{}, children:[{id:\"node4114\", name:\"4.114\", data:{}, children:[]}, {id:\"node4115\", name:\"4.115\", data:{}, children:[]}, {id:\"node4116\", name:\"4.116\", data:{}, children:[]}]}, {id:\"node3117\", name:\"3.117\", data:{}, children:[{id:\"node4118\", name:\"4.118\", data:{}, children:[]}, {id:\"node4119\", name:\"4.119\", data:{}, children:[]}, {id:\"node4120\", name:\"4.120\", data:{}, children:[]}, {id:\"node4121\", name:\"4.121\", data:{}, children:[]}]}, {id:\"node3122\", name:\"3.122\", data:{}, children:[{id:\"node4123\", name:\"4.123\", data:{}, children:[]}, {id:\"node4124\", name:\"4.124\", data:{}, children:[]}]}]}, {id:\"node2125\", name:\"2.125\", data:{}, children:[{id:\"node3126\", name:\"3.126\", data:{}, children:[{id:\"node4127\", name:\"4.127\", data:{}, children:[]}, {id:\"node4128\", name:\"4.128\", data:{}, children:[]}, {id:\"node4129\", name:\"4.129\", data:{}, children:[]}]}]}]}, {id:\"node1130\", name:\"1.130\", data:{}, children:[{id:\"node2131\", name:\"2.131\", data:{}, children:[{id:\"node3132\", name:\"3.132\", data:{}, children:[{id:\"node4133\", name:\"4.133\", data:{}, children:[]}, {id:\"node4134\", name:\"4.134\", data:{}, children:[]}, {id:\"node4135\", name:\"4.135\", data:{}, children:[]}, {id:\"node4136\", name:\"4.136\", data:{}, children:[]}, {id:\"node4137\", name:\"4.137\", data:{}, children:[]}]}]}, {id:\"node2138\", name:\"2.138\", data:{}, children:[{id:\"node3139\", name:\"3.139\", data:{}, children:[{id:\"node4140\", name:\"4.140\", data:{}, children:[]}, {id:\"node4141\", name:\"4.141\", data:{}, children:[]}]}, {id:\"node3142\", name:\"3.142\", data:{}, children:[{id:\"node4143\", name:\"4.143\", data:{}, children:[]}, {id:\"node4144\", name:\"4.144\", data:{}, children:[]}, {id:\"node4145\", name:\"4.145\", data:{}, children:[]}, {id:\"node4146\", name:\"4.146\", data:{}, children:[]}, {id:\"node4147\", name:\"4.147\", data:{}, children:[]}]}]}]}]}";
		return json;
	}

	public static String printNode(OntClass c, int position) {
		String s = "";

		if (position > 0)
			s += ",";
		s += "{\"id\":\"" + JenaUtils.generateNodeId(c) + "\", \"name\":\""
				+ JenaUtils.getCaption(c) + "\", \"data\":{}, \"children\":[";

		ExtendedIterator<OntClass> classes = c.listSubClasses(true);
		int nb = 0;
		while (classes.hasNext()) {
			OntClass subclass = classes.next();
			s += JenaUtils.printNode(subclass, nb);
			++nb;
		}

		s += "]}";

		return s;
	}

	public static String getCaption(OntClass c) {
		String caption = c.getLabel(null);

		if (caption == null) {
			try {
				// TODO améliorer pour les /../../
				Pattern p = Pattern.compile("#(.*)", Pattern.MULTILINE);
				Matcher m = p.matcher(c.getURI());
				m.find();
				caption = m.group(1);
			} catch (Exception e) {
				caption = c.getURI();
			}
		}

		// TODO gére mieux ça
		if (caption == null)
			caption = c.getLocalName();
		if (caption == null)
			caption = c.getComment(null);
		if (caption == null)
			caption = "";

		// Max 20 carac
		if (caption.length() > 20) {
			caption = "..." + caption.substring(caption.length() - 17);
		}

		return caption;
	}

	public static String generateNodeId(OntClass c) {
		String s = c.getURI() + System.currentTimeMillis() + Math.random();
		return String.valueOf(s.hashCode());
	}
}
