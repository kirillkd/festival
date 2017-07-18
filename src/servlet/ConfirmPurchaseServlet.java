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
 * Servlet implementation class ConfirmPurchaseServlet
 */
@WebServlet("/ConfirmPurchaseServlet")
public class ConfirmPurchaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmPurchaseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			int v_id = Integer.parseInt(request.getParameter("usr"));
			
			String productsBeanID = request.getParameter("beanId");
			ProductBean productsBean = (ProductBean) request.getSession().getAttribute(productsBeanID);
			//System.out.println(productsBean.getProductsList().get(1).getPrice());
			
			ArrayList<Integer> quantities = new ArrayList<Integer>();
			for (int i=0; i < request.getParameterValues("quantity").length; i++) {
				quantities.add(Integer.parseInt(request.getParameterValues("quantity")[i]));
			}
			
			ShopDAO dao = new ShopDAO();
			
			WristbandBean wb = new WristbandBean();
			wb.setV_id(v_id);
			
			dao.getWristbandBalance(wb, v_id);
			
			dao.computeOrderPrice(productsBean, quantities);
			
			String wristbandId = UUID.randomUUID().toString();
			//System.out.println("confirm  purchase servlet " + wristbandId);
			
			String amountID = UUID.randomUUID().toString();
			request.getSession().setAttribute(wristbandId, wb);
			request.getSession().setAttribute(amountID, productsBean.getSelectedProductsPrice());
			
			request.setAttribute("wristbandId", wristbandId);
			request.setAttribute("amountID", amountID);
			
			//System.out.println("confirm purchase wbid " + wb.getWristband_id());
			
			if (wb.getBalance() >= productsBean.getSelectedProductsPrice()) {
				//dao.alterWristbandBalance(wb, productsBean.getSelectedProductsPrice());
				request.setAttribute("productsBean", productsBean);
				request.setAttribute("quantities", quantities);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/confirmPurchase.jsp");
				dispatcher.forward(request, response);
			}
			else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/noBalance.jsp");
				dispatcher.forward(request, response);
			}
			//System.out.println(productsBean.getSelectedProductsPrice());
			//System.out.println(quantities.toString());
			
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
