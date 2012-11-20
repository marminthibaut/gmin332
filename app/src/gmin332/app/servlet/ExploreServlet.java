package gmin332.app.servlet;

import java.io.IOException;

import gmin332.app.utils.JenaUtils;
import gmin332.app.utils.ServletUtils;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

public class ExploreServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 455176115176745971L;
	private String hostname;

	// Init function
	public void init() {
		ServletConfig config = getServletConfig();

		this.hostname = config.getInitParameter("hostname");

		if (this.hostname == null) {
			this.hostname = "localhost";
			System.err
					.println("Please set init-param to define hostname. By default : localhost");
		}
	}

	// GET
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String accept = request.getHeader("Accept");
		if (accept == null)
			accept = "";

		if ("application/json".equals(request.getContentType())
				|| accept.contains("application/json")
				|| request.getRequestURI().endsWith("/json")) {

			System.out.println("Delivering json");

			HttpSession session = request.getSession();
			response.setContentType("application/json");

			String json = (String) session.getAttribute("json");

			response.getWriter().write(json);
		} else {
			System.out.println("Redirect to /");
			response.sendRedirect(ServletUtils.getBaseUrl(request));
		}

	}

	// POST
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		HttpSession session = request.getSession(true);

		String jsp_page = "/explore.jsp";
		try {

			String uri_ontology = request.getParameter("uri_ontology");
			if (uri_ontology != null && !uri_ontology.startsWith("http://")) {
				uri_ontology = "http://" + request.getParameter("uri_ontology");
			}

			OntModel model = JenaUtils.importModel(uri_ontology);

			session.setAttribute("json", JenaUtils.modelToJson(model));
			request.setAttribute("json_uri", "http://" + this.hostname
					+ request.getRequestURI() + "/json");

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("Exception", e);
			jsp_page = "/default.jsp";
		}

		getServletContext().getRequestDispatcher(jsp_page).forward(request,
				response);
	}
}
