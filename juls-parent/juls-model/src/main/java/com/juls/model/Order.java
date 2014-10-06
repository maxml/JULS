/**
 * 
 */
package com.juls.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;  
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


/**
 * Order Table entity 
 * version 1.0 30.09.2014
 * author Matvey Mitnitskyi
 * 
 */


@Entity
@Table(name = "orders")
@Component
@Scope("session")
public class Order implements Serializable{
	
	public Order(){
		setPaymentType(DEFAULT_PAYMENT_TYPE);
	}
	
	public Order(User user, Cart cart) {
		this.cart = cart;
		setId(UUID.randomUUID().toString());
		setDefaultOrderStatus();
		setOrderNum(UUID.randomUUID().toString());
		setPaymentType(DEFAULT_PAYMENT_TYPE);
		setUser(user);
	}
	
	public final static int UNCONFIRMED_ORDER_STATUS = -1;
	public final static int CONFIRMED_ORDER_STATUS = 1;
	public final static int ACCOMPLISHED_ORDER_STATUS = 2;
	public final static String DEFAULT_PAYMENT_TYPE = "not-established";
	
	@Id
	@Column(nullable = false)
	private String orders_id;
	
	@OneToOne
	@JoinColumn(name = "cart_id")
	private Cart cart;
	
	@Column(name = "numb", unique = true, nullable = false)
	private String orderNumber;
	
	@ManyToOne
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	private User user;
	
	@Column(name="payment_type", nullable = false)
	private String paymentType;

	@Column (name = "status", nullable = false)
	private int orderStatus;
	
	@Column (name = "total")
	private float totalPrice;
	
	public void setId(String id) {
		this.orders_id = id;
	}
	
	public String getId() {
		return orders_id;
	}
	
	public void setOrderNum(String orderNum) {
		this.orderNumber = orderNum;
	}
	
	public String getOrderNum() {
		return orderNumber;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setCurrentCart (Cart cart) {
		this.cart = cart;
	}
	
	public Cart getCurrentCart() {
		return cart;
	}
	
	public void setPaymentType(String type) {
		this.paymentType = type;
	}
	
	public String getPaymentType() {
		return paymentType;
	}
	
	public float getTotalPrice() {
		return totalPrice;
	}
	
	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	public int getOrderStatus() {
		return orderStatus;
	}
	
	public void setDefaultOrderStatus() {
		this.orderStatus = UNCONFIRMED_ORDER_STATUS;
	}
}
