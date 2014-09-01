package com.juls.model;

import java.util.UUID;

import javax.persistence.Column;
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
	
	public Good(String name, float price){
		setId(UUID.randomUUID().toString());
		setName(name);
		setPrice(price);
	}

	@Id
	@Column(nullable = false, unique = true)
	private String id;
	@Column(nullable = false, unique = true)
	private String name;
	@Column(nullable = false)
	private float price;
	
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
