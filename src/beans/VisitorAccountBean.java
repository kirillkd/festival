package beans;

import java.util.Date;

public class VisitorAccountBean {

	private String username;
	private String password;
	private Date filter_date;
	private Date alarm;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getFilter_date() {
		return filter_date;
	}
	public void setFilter_date(Date filter_date) {
		this.filter_date = filter_date;
	}
	public Date getAlarm() {
		return alarm;
	}
	public void setAlarm(Date alarm) {
		this.alarm = alarm;
	}
	
	
	
}
