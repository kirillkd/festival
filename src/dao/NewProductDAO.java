package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import beans.ShopBean;
import beans.GenericListBean;
import beans.VendorBean;

public class NewProductDAO extends DAO {
	
	public NewProductDAO() throws ClassNotFoundException, SQLException {
		getConnection();
	}
	
	
	public void getVendors(GenericListBean<VendorBean> vendorListBean) throws SQLException {
		String query = "select v.vendor_id, p.name " +
				"from vendor v, sponsor s, provider p " +
				"where v.sponsor_id = s.sponsor_id " +
				"and s.provider_id = p.provider_id";
		
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		vendorListBean.setItems(new LinkedList<VendorBean>());
		
		while (resultSet.next()) {
			VendorBean vendorBean = new VendorBean();
			
			vendorBean.setVendor_id(resultSet.getInt("vendor_id"));
			vendorBean.setName(resultSet.getString("name"));
			
			vendorListBean.getItems().add(vendorBean);
		}
		
		resultSet.close();
		preparedStatement.close();
	}
	
	
	public void getProductCategoryDropdownContent(GenericListBean<String> categoryListBean) throws SQLException {
		String query = "select unnest(enum_range(NULL::product_category));";
		
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		categoryListBean.setItems(new LinkedList<String>());
				
		while (resultSet.next()) {
			categoryListBean.getItems().add(resultSet.getString(1));
		}
		
		resultSet.close();
		preparedStatement.close();
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
