package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

import beans.ProductBean;
import dao.ShopDAO;

/**
 * Servlet implementation class ProductsServlet
 */
@WebServlet("/ProductsServlet")
public class ProductsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// Populate the productBean with all the products of the chosen shop
		try {
			
			int shopId = Integer.parseInt(request.getParameter("shopid"));
        	ShopDAO dao = new ShopDAO();
        	ProductBean product = new ProductBean();
        	dao.getShopProducts(product, shopId);
        	
        	// unique identifier for transferring the product bean to the next servlet
        	String productsBeanId = UUID.randomUUID().toString();
        	// set the session attribute for the next servlet
        	request.getSession().setAttribute(productsBeanId, product);
			
        	// the UUID needs to be given to the next servlet for the product bean to be retrieved
        	request.setAttribute("productsBeanId", productsBeanId);
        	// pass the productBean to the jsp
        	request.setAttribute("productsBean", product);
        	// pass the shopId to the jsp
        	request.setAttribute("shopID", shopId);

    	} catch (Throwable e) {
    		e.printStackTrace();
    		request.setAttribute("error", e.toString() + e.getMessage());
    	}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/showProducts.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
