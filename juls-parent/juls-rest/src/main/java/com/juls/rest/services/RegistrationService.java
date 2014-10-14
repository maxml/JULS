package com.juls.rest.services;

import java.util.Properties;
import java.util.UUID;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

import com.juls.model.Cart;
import com.juls.model.User;
import com.juls.model.UserDetails;
import com.juls.persist.UserDAOImpl;

@Service
public class RegistrationService {
	
	public User register(String email, String pass1, String pass2){
		if (pass1.equals(pass2)){
			User newUser = new User(email, pass1);
			newUser.setUserCart(new Cart(Cart.DEFAULT_CART_STATUS));
			newUser.setAdditionalInfo(new UserDetails("", "", "", ""));
			return newUser;
		}
		return null;
	}
	
	public boolean save(User user){
		
		user.setToken(UUID.randomUUID().toString()); //token 4 email verification
		return new UserDAOImpl().insert(user);
	}
	
	public boolean validate(String token){
		User toBeValidated = new UserDAOImpl().getByToken(token);
		if (toBeValidated.getRegStatus() == User.UNCONFIRMED){
			toBeValidated.setRegStatus(User.REGISTERED);
			return new UserDAOImpl().update(toBeValidated);
		}
		return false;
	}	
	
	public boolean sendConfirmationEmail(User target){
		String emailTo = target.getEmail();
		final String emailFrom = "julsinua@gmail.com";
		final String pass = "l;ekcby.f";
		
		Properties properties = System.getProperties();
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.socketFactory.port", "465");
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.port", "465");
		
		Session session = Session.getDefaultInstance(properties,
				new Authenticator(){
					protected PasswordAuthentication getPasswordAuthentication(){
						return new PasswordAuthentication(emailFrom, pass);
					}
		});
		
		try{
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(emailFrom));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
			message.setSubject("Account confirmation");
			message.setText(""
					+ "Thank you for registration at http://juls.in.ua. \n"
					+ "To complete registration, please, click on the confirmation link below. \n"
					+ Redirector.getEmailConfirmLink(target.getToken()) + "\n"
					+ "Please note, that this email doesn't need to be replied. If you didn't register at http://juls.in.ua please ignore this message" );
			Transport.send(message);
			return true;
		}
		catch(MessagingException ex){
			return false;
		}
	}
}
