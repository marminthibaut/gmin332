package gmin332.app.servlet;

import java.io.IOException;
import gmin332.app.utils.ServletUtils;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExploreServlet extends HttpServlet {
	private String param1;
	private String param2;

	// Init function
	public void init() {
		ServletConfig config = getServletConfig();
		/*
		 * this.param1 = config.getInitParameter("param1"); this.param2 =
		 * config.getInitParameter("param2");
		 * 
		 * if (this.param1 == null) this.param1 = "param1raté"; if (this.param2
		 * == null) this.param2 = "param2raté";
		 */
	}

	// GET
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.sendRedirect(ServletUtils.getBaseUrl(request));
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		getServletContext().getRequestDispatcher("/explore.jsp");
	}
}
