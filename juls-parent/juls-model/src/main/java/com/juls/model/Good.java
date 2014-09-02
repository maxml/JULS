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
@Table(name="good")
@JsonIgnoreProperties("orderGoods")
public class Good {
	
	public Good(){
		orderGoods = new LinkedList<OrderGood>();
	}
	
	public Good(String name, float price){
		setId(UUID.randomUUID().toString());
		setName(name);
		setPrice(price);
		orderGoods = new LinkedList<OrderGood>();
	}

	@Id
	@Column(nullable = false, unique = true)
	private String id;
	
	@Column(nullable = false, unique = true)
	private String name;
	
	@Column(nullable = false)
	private float price;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "id.good", cascade = CascadeType.ALL)
	private List<OrderGood> orderGoods;
	
	public List<OrderGood> getOrderGoods() {
		return orderGoods;
	}

	public void setOrderGoods(List<OrderGood> orderGoods) {
		this.orderGoods = orderGoods;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getId() {
		return id;
	}
	public void setId(String newId) {
		this.id = newId;
	}
}
