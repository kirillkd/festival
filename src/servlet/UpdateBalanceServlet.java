package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.WristbandBean;
import dao.ShopDAO;
/**
 * Servlet implementation class UpdateBalanceServlet
 */
@WebServlet("/UpdateBalanceServlet")
public class UpdateBalanceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateBalanceServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub	
		try {
			// get the UUID to retrieve the wristband bean
			String wbID = request.getParameter("wbID");
			
			// retrieve the wristband bean
			WristbandBean wb = (WristbandBean) request.getSession().getAttribute(wbID);
			
			ShopDAO dao = new ShopDAO();
			
			// get the total price of the selected items
			String amountID = request.getParameter("amountID");
			double amount = (double) request.getSession().getAttribute(amountID);
			
			// perform the payment transaction
			dao.alterWristbandBalance(wb, amount);
			
			// inform the user of success and the balance left
			request.setAttribute("newBalance", wb.getBalance());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/confirmation.jsp");
			dispatcher.forward(request, response);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
