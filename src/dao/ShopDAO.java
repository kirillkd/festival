package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import bean.ShopBean;
import bean.ShopListBean;

public class ShopDAO extends DAO {
	
	public ShopDAO() {
		
	}
	
	public void getVendorShops(ShopListBean shopListBean, int vendor_id) throws ClassNotFoundException, SQLException {
		Connection connection = getConnection();
		
		String query = "select * from shop where vendor_id = ?;";
		
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		
		preparedStatement.setInt(1, vendor_id);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		LinkedList<ShopBean> shops = new LinkedList<>();
		
		while (resultSet.next()) {
			ShopBean shopBean = new ShopBean();
			
			shopBean.setShop_id(resultSet.getInt("shop_id"));
			shopBean.setName(resultSet.getString("name"));
			shopBean.setCategory(resultSet.getString("category"));
			shopBean.setVendor_id(resultSet.getInt("vendor_id"));
			shopBean.setArea_type(resultSet.getString("area_type"));
			
			shops.add(shopBean);
		}
		
		shopListBean.setShops(shops);
		
		resultSet.close();
		preparedStatement.close();
		connection.close();
	}

}
