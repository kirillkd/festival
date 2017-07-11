package beans;

public class TimetableEntryBean {

	private int timetable_entry_id;
	private int band_id;
	private String username;
	private int preference;
	
	public int getTimetable_entry_id() {
		return timetable_entry_id;
	}
	public void setTimetable_entry_id(int timetable_entry_id) {
		this.timetable_entry_id = timetable_entry_id;
	}
	public int getBand_id() {
		return band_id;
	}
	public void setBand_id(int band_id) {
		this.band_id = band_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getPreference() {
		return preference;
	}
	public void setPreference(int preference) {
		this.preference = preference;
	}
	
	
	
}
