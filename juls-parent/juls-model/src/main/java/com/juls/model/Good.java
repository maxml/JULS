package com.juls.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Entity
@Table(name="good")
@Component
@Scope("session")
@JsonIgnoreProperties("orderGoods")
public class Good implements Serializable {

	public Good(){
		orderGoods = new LinkedList<CartGood>();
	}
	
	public Good(String name, float price, String shortDescription, String type, String mass, String composition){
		setId(UUID.randomUUID().toString());
		setName(name);
		setPrice(price);
		info = new GoodInfo();
		info.setShortDescription(shortDescription);
		info.setComposition(composition);
		info.setMass(mass);
		info.setType(type);
		orderGoods = new LinkedList<CartGood>();

	}
		
	@Id
	@Column(nullable = false, unique = true)
	private String id;
	
	@Column(nullable = false, unique = true)
	private String name;
	
	@Column(nullable = false)
	private float price;
	
	@Embedded
	private GoodInfo info;
	
	public GoodInfo getGoodInfo(){
		return info;
	}
	
	public void setGoodInfo(GoodInfo info){
		this.info = info;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "id.good", cascade = CascadeType.ALL)
	private List<CartGood> orderGoods;
	
	public List<CartGood> getOrderGoods() {
		return orderGoods;
	}

	public void setOrderGoods(List<CartGood> orderGoods) {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((info == null) ? 0 : info.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((orderGoods == null) ? 0 : orderGoods.hashCode());
		result = prime * result + Float.floatToIntBits(price);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Good))
			return false;
		Good other = (Good) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (info == null) {
			if (other.info != null)
				return false;
		} else if (!info.equals(other.info))
			return false;
		if (info != other.info)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (orderGoods == null) {
			if (other.orderGoods != null)
				return false;
		} else if (!orderGoods.equals(other.orderGoods))
			return false;
		if (Float.floatToIntBits(price) != Float.floatToIntBits(other.price))
			return false;
		return true;
	}

}
