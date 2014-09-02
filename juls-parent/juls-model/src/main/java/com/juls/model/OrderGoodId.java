package com.juls.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonManagedReference;

@Embeddable
public class OrderGoodId implements Serializable{
	
	@ManyToOne
	private Good good;
	
	@ManyToOne
	private Order2 order;

	public Good getGood() {
		return good;
	}

	public void setGood(Good good) {
		this.good = good;
	}

	public Order2 getOrder() {
		return order;
	}

	public void setOrder(Order2 order) {
		this.order = order;
	}
	
}
