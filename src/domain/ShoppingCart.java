package domain;

import java.util.HashSet;
import java.util.Set;

public class ShoppingCart {
	private Float total;
	private Set<ItemShoppingCart> itemShoppingCarts = new HashSet<ItemShoppingCart>();

	public Float getTotal() {
		return total;
	}

	public void setTotal(Float total) {
		this.total = total;
	}

	public Set<ItemShoppingCart> getItemShoppingCarts() {
		return itemShoppingCarts;
	}

	public void setItemShoppingCarts(Set<ItemShoppingCart> itemShoppingCarts) {
		this.itemShoppingCarts = itemShoppingCarts;
	}
	
}
