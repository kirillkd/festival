package beans;

import java.util.LinkedList;

public class GenericListBean<E> {
	
	private LinkedList<E> items;
	
	public GenericListBean() {
	}

	public LinkedList<E> getItems() {
		return items;
	}

	public void setItems(LinkedList<E> items) {
		this.items = items;
	}
}
