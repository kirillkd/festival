package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import beans.ProductBean;
import beans.ProductBean.product_category;
import beans.ShopBean;
import beans.WristbandBean;


public class ShopDAO extends DAO{
	
	public void getShops(ShopBean shop) throws SQLException, ClassNotFoundException {
		
		String query = "SELECT * FROM shop;";
		
		getConnection();
		
		
		PreparedStatement pstmt = connection.prepareStatement(query);
		
		
		ResultSet rs = pstmt.executeQuery();
		List<ShopBean> list = new ArrayList<ShopBean>();

		
		while(rs.next()) {
			ShopBean newshop = new ShopBean();
			newshop.setShop_id(rs.getInt("shop_id"));
			newshop.setName(rs.getString("name"));
			newshop.setCategory(rs.getString("category"));
			list.add(newshop);
		}
		shop.setShopList(list);
		//System.out.println(list);
		
		rs.close();
		pstmt.close();
		closeConnection();
	}

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
			while(rsProduct.next()){
				ProductBean newProduct = new ProductBean();
				newProduct.setProduct_id(rsProduct.getInt("product_id"));
				newProduct.setName(rsProduct.getString("name"));
				newProduct.setPrice(rsProduct.getDouble("price"));
				newProduct.setType(rsProduct.getString("type"));
				newProduct.setCategory(product_category.valueOf(rsProduct.getString("category")));
				productList.add(newProduct);
			}
		}
		productBean.setProductsList(productList);
	
		//System.out.println(productBean.getProductsList().get(2).getName());
		
		rs.close();
		pstmt.close();
		closeConnection();
	}
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
	
	public void computeOrderPrice (ProductBean productBean, List<Integer> quantities) throws SQLException, ClassNotFoundException {
		
		// total price of selected item
		double totalPrice = 0;
		for (int i = 0; i < quantities.size(); i++) {
			totalPrice += quantities.get(i) * productBean.getProductsList().get(i).getPrice();
		}
		productBean.setSelectedProductsPrice(totalPrice);
	}
	
	public void alterWristbandBalance (WristbandBean wb, double amount) throws SQLException, ClassNotFoundException {		
		
		getConnection();
		
		double newBalance = wb.getBalance() - amount;
		
		String changeBal = "UPDATE wristband SET balance=? WHERE visitor_id=" + wb.getV_id() + ";";
		//System.out.println("shopdao new balance: " + newBalance);
		
		PreparedStatement pstmt = connection.prepareStatement(changeBal);	
		pstmt.setDouble(1, newBalance);
		System.out.println("prep stat "+pstmt);
		System.out.println("shop dao query result: " + pstmt.executeUpdate());
		connection.commit();
		
		pstmt.close();
		closeConnection();
	}

}