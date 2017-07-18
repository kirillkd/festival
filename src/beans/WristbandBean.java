package beans;

public class WristbandBean {

	private int wristband_id;
	private boolean disabled;
	private double balance;
	private int v_id;
	
	public int getV_id() {
		return v_id;
	}
	public void setV_id(int v_id) {
		this.v_id = v_id;
	}
	public int getWristband_id() {
		return wristband_id;
	}
	public void setWristband_id(int wristband_id) {
		this.wristband_id = wristband_id;
	}
	public boolean isDisabled() {
		return disabled;
	}
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	
	
}
