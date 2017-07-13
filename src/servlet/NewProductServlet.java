package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.GenericListBean;
import beans.ShopBean;
import beans.VendorBean;

import dao.NewProductDAO;


@WebServlet("/new-product")
public class NewProductServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/newProduct.jsp");
		
		if (req.getParameter("vendor_id") == null) {
			try {
				GenericListBean<VendorBean> vendorListBean = new GenericListBean<>();
				
				NewProductDAO newProductDao = new NewProductDAO();
				
				newProductDao.getVendors(vendorListBean);
				req.setAttribute("vendorListBean", vendorListBean);
				
				newProductDao.closeConnection();
				
			} catch (ClassNotFoundException | SQLException e) {
				req.setAttribute("error", e.toString() + " " + e.getMessage());
				e.printStackTrace();
			}
			
		} else {
			try {
				GenericListBean<ShopBean> shopListBean = new GenericListBean<>();
				GenericListBean<String> categoryListBean = new GenericListBean<>();
				
				NewProductDAO newProductDao = new NewProductDAO();

				newProductDao.getVendorShops(shopListBean, Integer.parseInt(req.getParameter("vendor_id")));
				req.setAttribute("shopListBean", shopListBean);
				
				newProductDao.getProductCategoryDropdownContent(categoryListBean);
				req.setAttribute("categoryListBean", categoryListBean);
				
				newProductDao.closeConnection();
				
			} catch (NumberFormatException | ClassNotFoundException | SQLException e) {
				req.setAttribute("error", e.toString() + " " + e.getMessage());
				e.printStackTrace();
			}
		}
		
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		
		
	}
	
	
}
