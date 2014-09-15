package com.juls.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Entity
@Table(name="good")
@Component
@Scope("session")
public class Good {
	
	public Good(){
	
	}
	
	public Good(String name, float price, String description, String type, String mass, String composition){
		setId(UUID.randomUUID().toString());
		setName(name);
		setPrice(price);
		info = new GoodInfo();
		info.setDescription(description);
		info.setComposition(composition);
		info.setMass(mass);
		info.setType(type);
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
