package dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import beans.ProductBean;
import beans.ProductBean.product_category;
import beans.ShopBean;
import beans.WristbandBean;


public class ShopDAO extends DAO{
	
	// method to retrieve all the shops from the database
	public void getShops(ShopBean shop) throws SQLException, ClassNotFoundException {
		
		String query = "SELECT * FROM shop;";
		
		getConnection();
		
		PreparedStatement pstmt = connection.prepareStatement(query);
		
		ResultSet rs = pstmt.executeQuery();
		List<ShopBean> list = new ArrayList<ShopBean>();

		// for each row, we create a shop bean and set the attributes
		while(rs.next()) {
			ShopBean newshop = new ShopBean();
			newshop.setShop_id(rs.getInt("shop_id"));
			newshop.setName(rs.getString("name"));
			newshop.setCategory(rs.getString("category"));
			list.add(newshop);
		}
		shop.setShopList(list);
		
		rs.close();
		pstmt.close();
		closeConnection();
	}

	// method to retrieve all the products from the specified shop
	public void getShopProducts(ProductBean productBean, int shopId) throws SQLException, ClassNotFoundException {
	
		String query = "SELECT product_id FROM sold_in where shop_id=" + shopId;
	
		getConnection();
		
		PreparedStatement pstmt = connection.prepareStatement(query);
		
		ResultSet rs = pstmt.executeQuery();
		List <ProductBean> productList = new ArrayList<ProductBean>(); 

		while(rs.next()) {
			
			String queryProducts =  "SELECT * FROM product where product_id=" + rs.getInt("product_Id");
			PreparedStatement pstmtProduct = connection.prepareStatement(queryProducts);
			ResultSet rsProduct = pstmtProduct.executeQuery();
			
			// for each row, we create a product bean and set the attributes
			while(rsProduct.next()){
				
				ProductBean newProduct = new ProductBean();
				newProduct.setProduct_id(rsProduct.getInt("product_id"));
				newProduct.setName(rsProduct.getString("name"));
				newProduct.setPrice(rsProduct.getDouble("price"));
				newProduct.setType(rsProduct.getString("type"));
				newProduct.setCategory(product_category.valueOf(rsProduct.getString("category")));
				productList.add(newProduct);
			
			}
			rsProduct.close();
			pstmtProduct.close();
		}
		productBean.setProductsList(productList);
		
		rs.close();
		pstmt.close();
		closeConnection();
	}
	
	// method to get the current balance of the wristband of the specified visitor
	public void getWristbandBalance(WristbandBean wristBandBean, int visitorId) throws SQLException, ClassNotFoundException {
		
		String query = "SELECT balance FROM wristband where visitor_id=" + visitorId;
	
		getConnection();
		PreparedStatement pstmt = connection.prepareStatement(query);	
		ResultSet rs = pstmt.executeQuery();
	
		while(rs.next()) {
			wristBandBean.setBalance(rs.getDouble("balance"));
		}
			
		rs.close();
		pstmt.close();
		closeConnection();
	}
	
	// method to compute the total price of all selected items, with quantities taken into account
	public void computeOrderPrice (ProductBean productBean, List<Integer> quantities) throws SQLException, ClassNotFoundException {
		
		double totalPrice = 0;
		for (int i = 0; i < quantities.size(); i++) {
			totalPrice += quantities.get(i) * productBean.getProductsList().get(i).getPrice();
		}
		productBean.setSelectedProductsPrice(totalPrice);
	}
	
	// method to perform the transaction by subtracting the specified amount from the wristband balance
	public void alterWristbandBalance (WristbandBean wb, double amount) throws SQLException, ClassNotFoundException {		
		
		getConnection();
		
		// calculate and store the new balance
		double newBalance = wb.getBalance() - amount;
		wb.setBalance(newBalance);
		
		String changeBal = "UPDATE wristband SET balance=? WHERE visitor_id=" + wb.getV_id() + ";";
		
		PreparedStatement pstmt = connection.prepareStatement(changeBal);	
		pstmt.setDouble(1, newBalance);
		
		pstmt.executeUpdate();
		connection.commit();
		
		pstmt.close();
		closeConnection();
	}

}