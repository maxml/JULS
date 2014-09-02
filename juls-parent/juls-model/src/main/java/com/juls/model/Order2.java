package com.juls.model;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonManagedReference;

@Entity
@Table(name = "order2")
@JsonIgnoreProperties("orderGoods")
public class Order2 {
	
	public Order2(){
		orderGoods = new LinkedList<OrderGood>();
	}
	
	public Order2(int status){
		setId(UUID.randomUUID().toString());
		setStatus(status);
		orderGoods = new LinkedList<OrderGood>();
	}

	@Id
	@Column(nullable = false, unique = true)
	private String id;
	
	@Column(nullable = false)
	private int status;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "id.order", cascade = CascadeType.ALL)
	private List<OrderGood> orderGoods;
	
	public List<OrderGood> getOrderGoods() {
		return orderGoods;
	}

	public void setOrderGoods(List<OrderGood> orderGoods) {
		this.orderGoods = orderGoods;
	}

	public int getStatus() {
		return status;
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
}
