package beans;

public class SponsorBean extends ProviderBean {
	
	private int sponsor_id;
	private double money;

	public int getSponsor_id() {
		return sponsor_id;
	}

	public void setSponsor_id(int sponsor_id) {
		this.sponsor_id = sponsor_id;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}
}