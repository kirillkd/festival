package beans;

import java.util.Date;

public class festival_event {
	
	private int festival_event_id;
	private Date start_date;
	private Date end_date;
	private String name;
	private int location_id;
	
	public int getFestival_event_id() {
		return festival_event_id;
	}
	public void setFestival_event_id(int festival_event_id) {
		this.festival_event_id = festival_event_id;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLocation_id() {
		return location_id;
	}
	public void setLocation_id(int location_id) {
		this.location_id = location_id;
	}
}
	