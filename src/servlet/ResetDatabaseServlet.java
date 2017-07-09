package servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/resetDatabase")
public class ResetDatabaseServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		ServletContext context = getServletContext();
		String path = context.getRealPath("/database");
				
		Process p = Runtime.getRuntime().exec("python3 database.py", null, new File(path));
		
		try {
			p.waitFor();
			resp.sendRedirect(req.getContextPath() + "/index.jsp");
		} catch (InterruptedException e) {
			resp.sendError(502);
		}
	}	
}
