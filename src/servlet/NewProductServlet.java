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

import beans.GenericListBean;
import beans.ProductBean;
import beans.ProductBean.product_category;
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
				VendorBean vendorBean = new VendorBean();
				vendorBean.setVendor_id(Integer.parseInt(req.getParameter("vendor_id")));
				req.setAttribute("vendorBean", vendorBean);
				
				GenericListBean<ShopBean> shopListBean = new GenericListBean<>();
				GenericListBean<String> categoryListBean = new GenericListBean<>();
				
				NewProductDAO newProductDao = new NewProductDAO();

				newProductDao.getVendorShops(shopListBean, Integer.parseInt(req.getParameter("vendor_id")));
				req.setAttribute("shopListBean", shopListBean);
				
				newProductDao.getProductCategoryDropdownContent(categoryListBean);
				req.setAttribute("categoryListBean", categoryListBean);
				
				newProductDao.closeConnection();
				
			} catch (NumberFormatException | ClassNotFoundException | SQLException e) {
				req.setAttribute("error", e.getMessage());
				e.printStackTrace();
			}
		}
		
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		VendorBean vendorBean = new VendorBean();
		
		vendorBean.setVendor_id(Integer.parseInt(req.getParameter("inputVendorId")));
		
		ProductBean productBean = new ProductBean();
		
		productBean.setName(req.getParameter("inputProductName"));
		productBean.setPrice(Double.parseDouble(req.getParameter("inputProductPrice")));
		productBean.setType(req.getParameter("inputProductType"));
		productBean.setCategory(product_category.valueOf(req.getParameter("inputProductCategory")));
		
		req.setAttribute("productBean", productBean);
		
		GenericListBean<ShopBean> selectedShopListBean = new GenericListBean<>();
		
		selectedShopListBean.setItems(new LinkedList<ShopBean>());
		
		String[] selectedShops = req.getParameterValues("inputSoldIn");
		
		for (int i = 0; i< selectedShops.length; i++) {
			ShopBean shop = new ShopBean();
			
			shop.setShop_id(Integer.parseInt(selectedShops[i]));
			
			selectedShopListBean.getItems().add(shop);
		}

		GenericListBean<ShopBean> shopListBean = new GenericListBean<>();
		GenericListBean<String> categoryListBean = new GenericListBean<>();
		
		try {
			NewProductDAO newProductDao = new NewProductDAO();
			
			newProductDao.getVendorShops(shopListBean, Integer.parseInt(req.getParameter("inputVendorId")));
			req.setAttribute("shopListBean", shopListBean);
			
			newProductDao.getProductCategoryDropdownContent(categoryListBean);
			req.setAttribute("categoryListBean", categoryListBean);
			
			newProductDao.validateInput(productBean);
			
			newProductDao.createDatabaseEntries(vendorBean, productBean, selectedShopListBean);
			
			newProductDao.closeConnection();
			
			req.setAttribute("success", "A new product was successfully created!");		

			RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsp");
			dispatcher.forward(req, resp);
			
		} catch (ClassNotFoundException | SQLException | IllegalArgumentException e) {
			req.setAttribute("error", e.getMessage());
			e.printStackTrace();
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("/newProduct.jsp");
			dispatcher.forward(req, resp);
		}
	}	
}
