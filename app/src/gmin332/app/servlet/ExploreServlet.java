package gmin332.app.servlet;

import java.io.IOException;

import gmin332.app.utils.JenaUtils;
import gmin332.app.utils.ServletUtils;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

public class ExploreServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 455176115176745971L;

	// Init function
	public void init() {
		// ServletConfig config = getServletConfig();
		//
		// this.param1 = config.getInitParameter("param1"); this.param2 =
		// config.getInitParameter("param2");
		//
		// if (this.param1 == null) this.param1 = "param1raté"; if (this.param2
		// == null) this.param2 = "param2raté";

	}

	// GET
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.sendRedirect(ServletUtils.getBaseUrl(request));
	}

	// POST
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		String jsp_page = "/explore.jsp";
		try {

			String uri_ontology = "http://"
					+ request.getParameter("uri_ontology");
			System.out.println("URI_ONTOLOGY = " + uri_ontology);

			OntModel model = JenaUtils.importModel(uri_ontology);

			request.setAttribute("json", JenaUtils.modelToJson(model));

		} catch (Exception e) {
			request.setAttribute("Exception", e);
			System.out.println(e);
			jsp_page = "/default.jsp";
		}

		getServletContext().getRequestDispatcher(jsp_page).forward(request,
				response);
	}
}
