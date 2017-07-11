package beans;

import java.util.Date;

public class SaleBean {

	private int sale_id;
	private Date time;
	private int quantity;
	
	public int getSale_id() {
		return sale_id;
	}
	public void setSale_id(int sale_id) {
		this.sale_id = sale_id;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	

	
}
