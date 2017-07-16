package servlet;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

import beans.BandBean;
import beans.VisitorAccountBean;
import dao.CheckUsernameDAO;

/**
 * Servlet implementation class CheckUsernameServlet
 */
@WebServlet("/checkUsername")
public class CheckUsernameServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
				
		try {
			CheckUsernameDAO dao = new CheckUsernameDAO();
	    	VisitorAccountBean visitor_account = new VisitorAccountBean();
	    	ArrayList<BandBean> bands = new ArrayList<>();
	    	visitor_account.setUsername(req.getParameter("username"));
	    	visitor_account.setPassword(req.getParameter("password"));
	    	dao.getCheckUsername(visitor_account, bands);
	    	req.setAttribute("bands", bands);						
		}
		catch (SQLException | ClassNotFoundException | RuntimeException e) {
			req.setAttribute("error", e.getMessage());
		}
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/timetable.jsp");
		dispatcher.forward(req, resp);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/timetable.jsp");
		dispatcher.forward(request, response);
}
	
}
