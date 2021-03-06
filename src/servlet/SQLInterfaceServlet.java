package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.SQLResultBean;

import dao.SQLQueryDao;


@WebServlet("/sql-interface")
public class SQLInterfaceServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/sqlInterface.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		req.setAttribute("inputQuery", req.getParameter("inputQuery"));
		
		SQLQueryDao sqlQueryDao = new SQLQueryDao();
		
		try {
			sqlQueryDao.getConnection();
			SQLResultBean sqlResultBean = new SQLResultBean();
			
			// Pass query to the DAO and retrieve result
			sqlQueryDao.executeQuery(req.getParameter("inputQuery"), sqlResultBean);
        	req.setAttribute("sqlResult", sqlResultBean);
		} catch (ClassNotFoundException | SQLException e) {
			req.setAttribute("error", e.getMessage());		
			e.printStackTrace();	
		} finally {
			// Make sure to close the database connection
			try {
				sqlQueryDao.closeConnection();
			} catch (SQLException e) {
				req.setAttribute("error", e.getMessage());
				e.printStackTrace();
			}
		}

		RequestDispatcher dispatcher = req.getRequestDispatcher("/sqlInterface.jsp");
		dispatcher.forward(req, resp);
	}
}
