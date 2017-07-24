package beans;

import java.time.LocalDate;
import java.time.LocalTime;

public class BandBean extends ProviderBean {

	private int band_id;
	private boolean headliner;
	private LocalDate timeslot_date;
	private LocalTime timeslot_start;
	private LocalTime timeslot_end;
	private String press_information;
	private boolean is_cancelled;

	public int getBand_id() {
		return band_id;
	}

	public void setBand_id(int band_id) {
		this.band_id = band_id;
	}

	public boolean isHeadliner() {
		return headliner;
	}

	public void setHeadliner(boolean headliner) {
		this.headliner = headliner;
	}

	public LocalDate getTimeslot_date() {
		return timeslot_date;
	}

	public void setTimeslot_date(LocalDate timeslot_date) {
		this.timeslot_date = timeslot_date;
	}

	public LocalTime getTimeslot_start() {
		return timeslot_start;
	}

	public void setTimeslot_start(LocalTime timeslot_start) {
		this.timeslot_start = timeslot_start;
	}

	public LocalTime getTimeslot_end() {
		return timeslot_end;
	}

	public void setTimeslot_end(LocalTime timeslot_end) {
		this.timeslot_end = timeslot_end;
	}

	public String getPress_information() {
		return press_information;
	}

	public void setPress_information(String press_information) {
		this.press_information = press_information;
	}

	public boolean isIs_cancelled() {
		return is_cancelled;
	}

	public void setIs_cancelled(boolean is_cancelled) {
		this.is_cancelled = is_cancelled;
	}
}
