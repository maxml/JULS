package com.juls.rest.services;

import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import com.juls.model.Cart;
import com.juls.model.CartGood;
import com.juls.model.Good;
import com.juls.model.Order;
import com.juls.model.User;
import com.juls.persist.HibernateUtil;
import com.juls.persist.OrderDAOImpl;
import com.juls.persist.UserDAOImpl;

@Service
public class OrderService {
	
	SessionFactory sessionFactory = HibernateUtil.createSessionFactory();
	
//	public Cart getCurrentCart(User user) {
//		org.hibernate.Session session = sessionFactory.getCurrentSession();
//		Transaction tr = session.getTransaction();
//		tr.begin();
//		
//		
//	}
	
	public boolean sendConfirmationEmail(User target){		
		String emailTo = target.getEmail();
		final String emailFrom = "julsinua@gmail.com";
		final String pass = "l;ekcby.f";
		
			/* Returns order with UNCONFIRMED_ORDER_STATUS*/
		Order order = new OrderDAOImpl().getOrderByUser(target, -1);

		Properties properties = getProperties();
		
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
				message.setSubject("Order confirmation");
				message.setText(""
						+ "Thank you for using JULS Delivery!\n"
						+ "Your order number is "+order.getOrderNumb()+"\n"
						+ "Thank you for shopping with us! Our manager will contact you asap!\n"
						+ "\n"
						+ "If you need further assistance with your order, please visit Customer Service our website http://juls.in.ua\n"
						+ "We hope to see you again soon!\n"
						+ "\n"
						+ "Sincerely\n"
						+ "JULS Delivery Team!");
				Transport.send(message);
			
			/* Set CONFIRMED_ORDER_STATUS*/
			order.setStatus(Order.CONFIRMED_ORDER_STATUS);
			new OrderDAOImpl().update(order);
			
			/*Set null value for current user cart*/
			target.setUserCart(null);
			new UserDAOImpl().update(target);
			
			return true;
		}
		catch(MessagingException ex){
			return false;
		}
		catch (NullPointerException nullEx) {
			return false;
		}
	}
	
	public Properties getProperties() {
		Properties properties = System.getProperties();
		
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.socketFactory.port", "465");
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.port", "465");
		
		return properties;
	}
		
}