package beans;
import java.util.Date;

public class TicketPriceBean {
	private int ticket_price_id;
	private double price;
	private Date valid_from;
	private Date valid_to;
	private int ticket_type_id;
	
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
	public Date getValid_from() {
		return valid_from;
	}
	public void setValid_from(Date valid_from) {
		this.valid_from = valid_from;
	}
	public Date getValid_to() {
		return valid_to;
	}
	public void setValid_to(Date valid_to) {
		this.valid_to = valid_to;
	}
	public int getTicket_type_id() {
		return ticket_type_id;
	}
	public void setTicket_type_id(int ticket_type_id) {
		this.ticket_type_id = ticket_type_id;
	}
	
	
	
}
