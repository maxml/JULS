package com.juls.logger;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.juls.model.User;

/**
 * 
 * @author matvey
 *
 */


@Aspect
public class UserLogger {

	User currentUser;
	
		/*PointCuts*/
	@Pointcut("execution(* com.juls.rest.controllers.login.LoginController.userLogin (..))")
	public void julsRestLoginPointCut(){		
	}
	
	@Pointcut("execution(* com.juls.rest.controllers.sign.SignOutController.signOut (..))")
	public void julsRestSignOutPointCut(){	
	}
	
	@Pointcut("execution(* com.juls.rest.controllers.login.RegistrationController.register (..))")
	public void julsRestRegisterPointCut(){	
	}
	
	@Pointcut("execution(* com.juls.rest.controllers.login.RegistrationController.validate (..))")
	public void julsRestValidatePointCut(){
	}
	
	@Pointcut("within(com.juls.rest.controllers.login.LoginController)")
	public void julsRestLoginControllerPointCut(){		
	}
	
		/*Advice*/
	@After("julsRestLoginPointCut())")
	public void afterLogin(){
		System.out.println("*******After Login***********");
	}	
	
	
	@After("julsRestLoginControllerPointCut()")
	public void inTheLoginControllerClass(JoinPoint joinPoint) {
		System.out.println("*******inside " + joinPoint.getTarget().toString() 
				+ "class***********");
	}	
		
	@After("julsRestSignOutPointCut() ")
	public void afterSignOut(){
		System.out.println("*********After Logout**********");
	}
	
	@After("julsRestRegisterPointCut()")
	public void afterRegister(){
		System.out.println("*******After Register***********");
	}
	
	@After("julsRestValidatePointCut()")
	public void afterValidate(){
		System.out.println("*******After Validate***********");
	}		
}
