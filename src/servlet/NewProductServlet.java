package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ProductBean;
import bean.ShopBean;
import bean.ShopListBean;

import dao.NewProductDAO;
import dao.ShopDAO;


@WebServlet("/new-product")
public class NewProductServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/new-product.jsp");
		
		ShopListBean shopListBean = new ShopListBean();
		
		ShopDAO shopDao = new ShopDAO();
		
		try {
			shopDao.getVendorShops(shopListBean, Integer.parseInt(req.getParameter("vendor_id")));
			req.setAttribute("shopListBean", shopListBean);
		} catch (NumberFormatException | ClassNotFoundException | SQLException e) {
			req.setAttribute("error", e.toString()+ " " + e.getMessage());
			e.printStackTrace();
		}		
		
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		
		
	}
	
	
}
