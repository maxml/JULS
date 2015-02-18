package com.juls.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Entity
@Table(name="user")
@Component
@Scope("session")
public class User implements Serializable {
	
	public static final int REGISTERED = 1;
	public static final int UNCONFIRMED = 0;
	@Transient
	private Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Europe/Kiev"));
//	private DateFormat df = new SimpleDateFormat();
	@Transient
	private Date timeNow;
	
	@Id
	@Column(nullable = false, unique = true)
	private String id;
	
	@Column(nullable = false, unique = true)
	private String email;
	
	@Column(unique = true)
	private String nickName;
	
	@Column(nullable = false)
	private String password;
	
	@Column(name = "reg_status")
	private int regStatus;
	
	@Column(unique = true)
	private String token;
	
	@Column(nullable = false)
	private Date createDate;
	
	@OneToOne(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn
	private UserDetails additionalInfo;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn
	private Cart userCart;
	
	@OneToMany(mappedBy="user")
	private List<Order> orders;
	
	public User(){
		timeNow = new Date(Calendar.getInstance().getTimeInMillis());
		setCreateDate(timeNow);
	}
	
	public User(String email, String password){
		setId(UUID.randomUUID().toString());
		setEmail(email);
		setPassword(password);
		setRegStatus(UNCONFIRMED);
		timeNow = new Date(Calendar.getInstance().getTimeInMillis());
		setCreateDate(timeNow);
	}
	
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getId() {
		return id;
	}
	public void setId(String newId) {
		this.id = newId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String newEmail) {
		this.email = newEmail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UserDetails getAdditionalInfo() {
		return additionalInfo;
	}
	public void setAdditionalInfo(UserDetails additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	public int getRegStatus() {
		return regStatus;
	}

	public void setRegStatus(int regStatus) {
		this.regStatus = regStatus;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Cart getUserCart() {
		return userCart;
	}

	public void setUserCart(Cart userCart) {
		this.userCart = userCart;
	}

}
