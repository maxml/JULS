package com.juls.model;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="cart_to_good")
@AssociationOverrides({
	@AssociationOverride(name = "id.cart", 
		joinColumns = @JoinColumn(name = "id_cart")),
	@AssociationOverride(name = "id.good", 
		joinColumns = @JoinColumn(name = "id_good")) })
public class CartGood {
	
	@EmbeddedId
	private CartGoodId id = new CartGoodId();
	
	@Column(name = "good_amount")
	private long goodAmount;
	

	public CartGoodId getId() {
		return id;
	}

	public void setId(CartGoodId id) {
		this.id = id;
	}

	public long getGoodAmount() {
		return goodAmount;
	}

	public void setGoodAmount(long goodAmount) {
		this.goodAmount = goodAmount;
	}

	@Transient
	public Good getGood() {
		return getId().getGood();
	}

	public void setGood(Good good) {
		getId().setGood(good);
	}

	@Transient
	public Cart getCart() {
		return getId().getCart();
	}

	public void setCart(Cart cart) {
		getId().setCart(cart);
	}
	
	
	
}
