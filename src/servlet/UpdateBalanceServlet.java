package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.ProductBean;
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
			String wbID = request.getParameter("wbID");
			//System.out.println("update balance servlet " + wbID);
			WristbandBean wb = (WristbandBean) request.getSession().getAttribute(wbID);
			System.out.println("update balance servlet " + wb.getBalance());
			ShopDAO dao = new ShopDAO();
			
			String amountID = request.getParameter("amountID");
			double amount = (double) request.getSession().getAttribute(amountID);
			System.out.println("update balance amount: " + amount);
			dao.alterWristbandBalance(wb, amount);
			/*
			RequestDispatcher dispatcher = request.getRequestDispatcher("/showProducts.jsp");
			dispatcher.forward(request, response);*/
			
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
