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

import com.juls.model.Order;
import com.juls.model.User;
import com.juls.model.UserDetails;
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

	public void setAdditionalInfo(User user, String firstName, String lastName,
			String address, String phone) {
		
		org.hibernate.Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		
		UserDAOImpl userDAO = new UserDAOImpl();
		OrderDAOImpl orderDAO = new OrderDAOImpl();
		
		user = userDAO.getById(user.getId());
		Order order = orderDAO.findOrderByCart(user.getUserCart());
		
		order.setAddress(address);
		order.setfName(firstName);
		order.setlName(lastName);
		order.setPhone(phone);
		
		orderDAO.update(order);
		
		transaction.commit();
	}

	public UserDetails getOrderDetails(User user) {
		org.hibernate.Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		
		UserDAOImpl userDAO = new UserDAOImpl();
		OrderDAOImpl orderDAO = new OrderDAOImpl();
		
		user = userDAO.getById(user.getId());
//		Cart cart = user.getUserCart();
		Order order = orderDAO.getOrderByUser(user);
		
		UserDetails details = new UserDetails();
		if(!fullInitUserDetailsByOrder(details, order))
			initUserByDefault(details, user.getAdditionalInfo());
		
		transaction.commit();
		
		return details;
	}
		
	private void initUserByDefault(UserDetails orderDetails, UserDetails userDetails) {
		if (orderDetails == null || userDetails == null) 
			return;
		
		if(orderDetails.getAddress() == null)
			orderDetails.setAddress(userDetails.getAddress());
		if(orderDetails.getFirstName() == null)
			orderDetails.setFirstName(userDetails.getFirstName());
		if(orderDetails.getLastName() == null)
			orderDetails.setLastName(userDetails.getLastName());
		if(orderDetails.getMobilePhoneNumber() == null)
			orderDetails.setMobilePhoneNumber(userDetails.getMobilePhoneNumber());
	}

	private boolean fullInitUserDetailsByOrder(UserDetails userDetails, Order order) {
		if(userDetails == null || order == null)
			return false;
		
		boolean fullInit = true;
		String orderFirstName = order.getfName();
		String orderLastName = order.getlName();
		String orderPhone = order.getPhone();
		String orderAddress = order.getAddress();
		
		if(orderAddress == null)
			fullInit = false;
		else 
			userDetails.setAddress(orderAddress);
		
		if(orderFirstName == null)
			fullInit = false;
		else
			userDetails.setFirstName(orderFirstName);
		
		if(orderLastName == null)
			fullInit = false;
		else
			userDetails.setLastName(orderLastName);
		
		if(orderPhone == null)
			fullInit = false;
		else
			userDetails.setMobilePhoneNumber(orderPhone);
		
		return fullInit;
	}
}