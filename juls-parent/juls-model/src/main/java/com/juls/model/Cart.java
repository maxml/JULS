package com.juls.model;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.context.annotation.Scope;

@Entity
@Table(name = "cart")
@Scope("session")
@JsonIgnoreProperties("cartGoods")
public class Cart {
	
	public static final int DEFAULT_CART_STATUS = 1;
	public static final int TEST_CART_STATUS = 111;
	
	@Id
	@Column(name="cart_id", nullable = false, unique = true)
	private String id;
	
	@Column(nullable = false)
	private int status;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "id.cart", cascade = CascadeType.ALL)
	private List<CartGood> cartGoods;
	
    @OneToOne(mappedBy="cart")
	@JoinColumn
	private Order order;
	
    @Column(name = "total_price", nullable = false, columnDefinition = "float default 0.0")
    private float totalPrice;
    
	public List<CartGood> getCartGoods() {
		return cartGoods;
	}
	
	public Cart(){
		cartGoods = new LinkedList<CartGood>();
	}
	
	public Cart(int status){
		setId(UUID.randomUUID().toString());
		setStatus(status);
		cartGoods = new LinkedList<CartGood>();
	}
	
	public void addGood(Good good, long amount) {
		CartGood goodInCart = new CartGood();
		goodInCart.setGood(good);
		goodInCart.setCart(this);
		goodInCart.setGoodAmount(amount);
		cartGoods.add(goodInCart);
	}
	
	public void addGood(Good good) {
		addGood(good, 1);
	}
	
	public void setCartGoods(List<CartGood> cartGoods) {
		this.cartGoods = cartGoods;
	}

	public int getStatus() {
		return status;
	}
	
	public void setOrder(Order order) {
		this.order = order;
	}
	
	public Order getOrder() {
		return order;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getId() {
		return id;
	}
	public void setId(String newId) {
		this.id = newId;
	}

	public float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}
}
