package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import beans.ShopBean;
import beans.GenericListBean;

public class NewProductDAO extends DAO {
	
	public NewProductDAO() throws ClassNotFoundException, SQLException {
		getConnection();
	}
	
	public void getProductCategoryDropdownContent(GenericListBean<String> categoryListBean) throws SQLException {
		String query = "select unnest(enum_range(NULL::product_category));";
		
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		categoryListBean.setItems(new LinkedList<String>());
				
		while (resultSet.next()) {
			categoryListBean.getItems().add(resultSet.getString(1));
		}
	}

	public void getVendorShops(GenericListBean<ShopBean> shopListBean, int vendor_id) throws SQLException {		
		String query = "select shop_id, name, category from shop where vendor_id = ?;";
		
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		
		preparedStatement.setInt(1, vendor_id);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		shopListBean.setItems(new LinkedList<ShopBean>());
		
		while (resultSet.next()) {
			ShopBean shopBean = new ShopBean();
			
			shopBean.setShop_id(resultSet.getInt("shop_id"));
			shopBean.setName(resultSet.getString("name"));
			shopBean.setCategory(resultSet.getString("category"));
			
			shopListBean.getItems().add(shopBean);
		}
				
		resultSet.close();
		preparedStatement.close();
	}
}
