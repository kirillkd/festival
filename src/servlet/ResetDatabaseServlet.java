package servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.RequestDispatcher;
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
		
		BufferedReader stdErrorReader = new BufferedReader(new InputStreamReader(p.getErrorStream()));
		
		String error = "";
		String s;
		
		while ((s = stdErrorReader.readLine()) != null) {
			error += s + "\n";
		}
		
		try {
			p.waitFor();
			
			if (!error.equals("")) {
				req.setAttribute("error", error);
			} else {
				req.setAttribute("success", "Database reset successful!");
			}
		} catch (InterruptedException e) {
			req.setAttribute("error", e.getMessage());
			e.printStackTrace();
		}

		RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsp");
		dispatcher.forward(req, resp);
	}	
}
