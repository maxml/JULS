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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


/**
 * Order Table entity 
 * version 1.0 30.09.2014
 * author Matvey Mitnitskyi
 * 
 */


@Entity
@Table(name = "orders", 
uniqueConstraints=@UniqueConstraint(columnNames="numb"))
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
		setOrderNumb(UUID.randomUUID().toString());
		setPaymentType(DEFAULT_PAYMENT_TYPE);
		setUser(user);
	}
	
	public final static int UNCONFIRMED_ORDER_STATUS = -1;
	public final static int CONFIRMED_ORDER_STATUS = 1;
	public final static int ACCOMPLISHED_ORDER_STATUS = 2;
	public final static int INACTIVE_ORDER_STATUS = 0;
	
	public final static String DEFAULT_PAYMENT_TYPE = "not-established";
	public final static String CASH = "cash";
	public final static String LYQ_PAY = "lyq-pay";
	public final static String PAYPALL = "paypall";
	
	@Id 
	@Column(nullable = false)
	private String orders_id;
	
	@OneToOne
	 @JoinColumn(name = "cart_id")
	 private Cart cart;
		
	@Column(name = "numb", unique = true, nullable = false)
	private String orderNumber;
	
	@ManyToOne
	@JoinColumn(name = "user_id")	
	private User user;
	
	@Column(name="payment_type", nullable = false)
	private String paymentType;

	@Column (name = "status")
	private int orderStatus;
	
	@Column(name = "first_name")
	private String fName;
	
	@Column(name = "last_name")
	private String lName;
	
	private String phone;
	private String address;
	
	public void setId(String id) {
		this.orders_id = id;
	}
	
	public String getId() {
		return orders_id;
	}
	
	public void setOrderNumb(String orderNumb) {
		this.orderNumber = orderNumb;
	}
	
	public String getOrderNumb() {
		return orderNumber;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setStatus(int status) {
		this.orderStatus = status;
	}
	
	public int getStatus() {
		return orderStatus;
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
	
	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setDefaultOrderStatus() {
		this.orderStatus = UNCONFIRMED_ORDER_STATUS;
	}
}
