package beans;
import java.util.*;

public class ShopBean {

	private int shop_id;
	private String name;
	private String category;
	private List<ShopBean> shopList;
	
	public List<ShopBean> getShopList() {
		return shopList;
	}
	public void setShopList(List<ShopBean> shopList) {
		this.shopList = shopList;
	}
	public int getShop_id() {
		return shop_id;
	}
	public void setShop_id(int shop_id) {
		this.shop_id = shop_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	

	
}
