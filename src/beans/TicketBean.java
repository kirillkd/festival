package beans;

import java.util.Date;

public class TicketBean {

		private int ticket_id;
		private Date booking_date;
		private String payment_method;
		
		public int getTicket_id() {
			return ticket_id;
		}
		public void setTicket_id(int ticket_id) {
			this.ticket_id = ticket_id;
		}
		public Date getBooking_date() {
			return booking_date;
		}
		public void setBooking_date(Date booking_date) {
			this.booking_date = booking_date;
		}
		public String getPayment_method() {
			return payment_method;
		}
		public void setPayment_method(String payment_method) {
			this.payment_method = payment_method;
		}
		
		
	
	

}


