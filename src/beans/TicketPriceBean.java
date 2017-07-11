package beans;

public class TicketPriceBean {

	private int ticket_price_id;
	private double price;
	private String valid_from;
	private String valid_to;
	
	public int getTicket_price_id() {
		return ticket_price_id;
	}
	public void setTicket_price_id(int ticket_price_id) {
		this.ticket_price_id = ticket_price_id;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getValid_from() {
		return valid_from;
	}
	public void setValid_from(String valid_from) {
		this.valid_from = valid_from;
	}
	public String getValid_to() {
		return valid_to;
	}
	public void setValid_to(String valid_to) {
		this.valid_to = valid_to;
	}
	
	
	
}
