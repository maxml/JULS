package com.juls.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="userdetails")
public class UserDetails {
	public UserDetails(){
		
	}
	
	public UserDetails(String firstName, String lastName, String address, String mobNum){
		setId(UUID.randomUUID().toString());
		setFirstName(firstName);
		setLastName(lastName);
		setAddress(address);
		setMobilePhoneNumber(mobNum);
	}
	
	@Id
	@Column(nullable = false, unique=true)
	private String id;
	
	@Column(nullable = false)
	private String firstName;
	@Column(nullable = false)
	private String lastName;
	@Column(nullable = false)
	private String address;
	@Column(nullable = false)
	private String mobilePhoneNumber;
	
	public String getId() {
		return id;
	}
	public void setId(String newId) {
		this.id = newId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMobilePhoneNumber() {
		return mobilePhoneNumber;
	}
	public void setMobilePhoneNumber(String mobilePhoneNumber) {
		this.mobilePhoneNumber = mobilePhoneNumber;
	}

}
