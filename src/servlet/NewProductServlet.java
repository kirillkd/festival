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
		
		NewProductDAO newProductDao = new NewProductDAO();
		
		try {
			newProductDao.getConnection();
			
			if (req.getParameter("vendor_id") == null) { 
				GenericListBean<VendorBean> vendorListBean = new GenericListBean<>();
				
				newProductDao.getVendors(vendorListBean);
				req.setAttribute("vendorListBean", vendorListBean);
			} else {					
				VendorBean vendorBean = new VendorBean();
				
				vendorBean.setVendor_id(Integer.parseInt(req.getParameter("vendor_id")));
				req.setAttribute("vendorBean", vendorBean);
				
				GenericListBean<ShopBean> shopListBean = new GenericListBean<>();
				GenericListBean<String> categoryListBean = new GenericListBean<>();
				
				newProductDao.getVendorShops(shopListBean, Integer.parseInt(req.getParameter("vendor_id")));
				req.setAttribute("shopListBean", shopListBean);
				
				newProductDao.getProductCategoryDropdownContent(categoryListBean);
				req.setAttribute("categoryListBean", categoryListBean);
			}					
		} catch (ClassNotFoundException | SQLException | NumberFormatException e) {
			req.setAttribute("error", e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				newProductDao.closeConnection();
			} catch (SQLException e) {
				req.setAttribute("error", e.getMessage());
				e.printStackTrace();
			}
		}		

		RequestDispatcher dispatcher = req.getRequestDispatcher("/newProduct.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		VendorBean vendorBean = new VendorBean();
		
		vendorBean.setVendor_id(Integer.parseInt(req.getParameter("inputVendorId")));
		
		req.setAttribute("vendorBean", vendorBean);
		
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
		
		req.setAttribute("selectedShops", selectedShops);

		GenericListBean<ShopBean> shopListBean = new GenericListBean<>();
		GenericListBean<String> categoryListBean = new GenericListBean<>();
		
		NewProductDAO newProductDao = new NewProductDAO();
		
		try {
			newProductDao.getConnection();
			
			newProductDao.getVendorShops(shopListBean, Integer.parseInt(req.getParameter("inputVendorId")));
			req.setAttribute("shopListBean", shopListBean);
			
			newProductDao.getProductCategoryDropdownContent(categoryListBean);
			req.setAttribute("categoryListBean", categoryListBean);
			
			newProductDao.validateInput(productBean);
			
			newProductDao.createDatabaseEntries(vendorBean, productBean, selectedShopListBean);
			
			req.setAttribute("success", "A new product was successfully created!");		

			RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsp");
			dispatcher.forward(req, resp);
			
		} catch (ClassNotFoundException | SQLException | IllegalArgumentException e) {
			req.setAttribute("error", e.getMessage());
			e.printStackTrace();
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("/newProduct.jsp");
			dispatcher.forward(req, resp);
		} finally {
			try {
				newProductDao.closeConnection();
			} catch (SQLException e) {
				req.setAttribute("error", e.getMessage());
				e.printStackTrace();
			}
		}
	}
}
