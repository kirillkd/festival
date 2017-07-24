package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.UUID;

import dao.ShopDAO;
import beans.ProductBean;
import beans.WristbandBean;
/**
 * Servlet implementation class BasketServlet
 */
@WebServlet("/BasketServlet")
public class BasketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BasketServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			
			ShopDAO dao = new ShopDAO();
			
			// get the visitor id input and validate it (it should be an integer)
			int v_id = 0;
			try { 
				v_id = Integer.parseInt(request.getParameter("usr"));
			}
			catch (Exception e){
				request.setAttribute("errorMsg", "Visitor ID should be an integer");
				request.setAttribute("shopID", request.getParameter("shopID"));
				RequestDispatcher dispatcher = request.getRequestDispatcher("/purchaseError.jsp");
				dispatcher.forward(request, response);
				return;
			}
			
			// check if the supplied visitor id exists
			if (dao.isVisitorIDValid(v_id) == 0) {
				request.setAttribute("errorMsg", "Visitor ID does not exist");
				request.setAttribute("shopID", request.getParameter("shopID"));
				RequestDispatcher dispatcher = request.getRequestDispatcher("/purchaseError.jsp");
				dispatcher.forward(request, response);
				return;
			}
			
			// retrieve the UUID and then the productsBean itself
			// this is to display the selected items
			String productsBeanID = request.getParameter("beanId");
			ProductBean productsBean = (ProductBean) request.getSession().getAttribute(productsBeanID);
			
			// get the quantities (including zeroes)
			ArrayList<Integer> quantities = new ArrayList<Integer>();
			for (int i=0; i < request.getParameterValues("quantity").length; i++) {
				quantities.add(Integer.parseInt(request.getParameterValues("quantity")[i]));
			}
			
			// if no items  were selected (i.e. all quantities=0), display error page
			int nSelectedItems = 0;
			for (int i = 0; i < quantities.size(); i++) {
				if (quantities.get(i) != 0) { nSelectedItems++; }
			}
			if (nSelectedItems == 0) {
				request.setAttribute("errorMsg", "No items were selected");
				request.setAttribute("shopID", request.getParameter("shopID"));
				RequestDispatcher dispatcher = request.getRequestDispatcher("/purchaseError.jsp");
				dispatcher.forward(request, response);
				return;
			}
			
			
			WristbandBean wb = new WristbandBean();
			wb.setV_id(v_id);
			
			dao.getWristbandBalance(wb, v_id);
			
			dao.computeOrderPrice(productsBean, quantities);
			
			// UUID to pass the wristband bean to the next servlet (UpdateBalanceServlet)
			String wristbandId = UUID.randomUUID().toString();
			// UUID to pass the amount to the next servlet (UpdateBalanceServlet)
			String amountID = UUID.randomUUID().toString();
			// pass the wristband bean and the amount to the next servlet
			request.getSession().setAttribute(wristbandId, wb);
			request.getSession().setAttribute(amountID, productsBean.getSelectedProductsPrice());
			
			// pass the UUIDs for the servlet to retrieve the bean and amount
			request.setAttribute("wristbandId", wristbandId);
			request.setAttribute("amountID", amountID);
			
			// if balance of wristband is enough, show the selected products to user
			if (wb.getBalance() >= productsBean.getSelectedProductsPrice()) {

				request.setAttribute("productsBean", productsBean);
				request.setAttribute("quantities", quantities);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/basket.jsp");
				dispatcher.forward(request, response);
				return;
			}
			else { // otherwise display error message
				request.setAttribute("errorMsg", "Not enough balance. Top up your wristband!");
				request.setAttribute("shopID", request.getParameter("shopID"));
				RequestDispatcher dispatcher = request.getRequestDispatcher("/purchaseError.jsp");
				dispatcher.forward(request, response);
				return;
			}
			
		} catch (Throwable e) {
    		e.printStackTrace();
    		request.setAttribute("error", e.toString() + e.getMessage());
    	}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
