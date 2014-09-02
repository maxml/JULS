package com.juls.model;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


//@Entity
//@Table(name="order")
public class Order implements Serializable{

//	@Id 
//	@Column(nullable = false, unique = true)
//	private String id;
//	
//	@Column(name = "order_status", nullable = false)
//	private int status;
//	
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "id.order", cascade=CascadeType.ALL)
//	private List<OrderGood> goods;
//	
//	public Order() {}
//	
//	public Order(int status, List<OrderGood> goods) {
//		setId(UUID.randomUUID().toString());
//		this.status = status;
//		this.goods = goods;
//	}
//
//	public String getId() {
//		return id;
//	}
//
//	public void setId(String id) {
//		this.id = id;
//	}
//
//	public int getStatus() {
//		return status;
//	}
//
//	public void setStatus(int status) {
//		this.status = status;
//	}
//
//	public List<OrderGood> getGoods() {
//		return goods;
//	}
//
//	public void setGoods(List<OrderGood> goods) {
//		this.goods = goods;
//	}
//	
}
