package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

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

		boolean login = false;
		
		CheckUsernameDAO checkUsernameDao = new CheckUsernameDAO();
		
		try {			
			checkUsernameDao.getConnection();
			
			// Get all usernames, passwords of visitor_accounts, and bands
			VisitorAccountBean visitor_account = new VisitorAccountBean();
			ArrayList<BandBean> bands = new ArrayList<>();
			visitor_account.setUsername(req.getParameter("username"));
			visitor_account.setPassword(req.getParameter("password"));
			
			// Check, if username and password match
			checkUsernameDao.getCheckUsername(visitor_account, bands);
			req.setAttribute("bands", bands);
			req.setAttribute("username", visitor_account.getUsername());
			
			// Login is set to true, if there has not been any error. So username and password have matched.
			login = true;

		} catch (SQLException | ClassNotFoundException | RuntimeException e) {
			req.setAttribute("error", e.getMessage());
			e.printStackTrace();
		} finally {
			// Make sure to close the database connection
			try {
				checkUsernameDao.closeConnection();
			} catch (SQLException e) {
				req.setAttribute("error", e.getMessage());
				e.printStackTrace();
			}
		}
		
		
		// Login was succesful
		if (login == true) {
			RequestDispatcher dispatcher = req.getRequestDispatcher("/timetable.jsp");
			dispatcher.forward(req, resp);
		} else {
			// Login was not succesful
			RequestDispatcher dispatcher = req.getRequestDispatcher("/timetable_login.jsp");
			dispatcher.forward(req, resp);
		}
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/timetable_login.jsp");
		dispatcher.forward(request, response);
	}
}
