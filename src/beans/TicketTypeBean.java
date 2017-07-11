package beans;
import java.util.Date;

public class TicketTypeBean {
	private int ticket_type_id;
	private String type;
	private Date arrival_day;
	private Date departure_day;
	
	public int getTicket_type_id() {
		return ticket_type_id;
	}
	public void setTicket_type_id(int ticket_type_id) {
		this.ticket_type_id = ticket_type_id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getArrival_day() {
		return arrival_day;
	}
	public void setArrival_day(Date arrival_day) {
		this.arrival_day = arrival_day;
	}
	public Date getDeparture_day() {
		return departure_day;
	}
	public void setDeparture_day(Date departure_day) {
		this.departure_day = departure_day;
	}
}
