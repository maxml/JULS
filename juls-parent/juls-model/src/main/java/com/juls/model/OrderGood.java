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
@Table(name="order_to_good")
@AssociationOverrides({
	@AssociationOverride(name = "id.order", 
		joinColumns = @JoinColumn(name = "id_order")),
	@AssociationOverride(name = "id.good", 
		joinColumns = @JoinColumn(name = "id_good")) })
public class OrderGood {
	
	@EmbeddedId
	private OrderGoodId id = new OrderGoodId();
	
	@Column(name = "good_amount")
	private long goodAmount;
	

	public OrderGoodId getId() {
		return id;
	}

	public void setId(OrderGoodId id) {
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
	public Order2 getOrder() {
		return getId().getOrder();
	}

	public void setOrder(Order2 order) {
		getId().setOrder(order);
	}
	
	
	
}
