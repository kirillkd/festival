package beans;

public class ProductBean {

	private int product_id;
	private String name;
	private double price;
	private String type;
	private enum product_category{
		beverage, food, clothes, accessory
	}
	private product_category category;
	
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public product_category getProduct_Category(){
		return category;
	}
	public void setProduct_Category(product_category category){
		this.category = category;
	}

}