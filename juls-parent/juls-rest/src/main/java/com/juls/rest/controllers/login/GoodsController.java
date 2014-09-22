package com.juls.rest.controllers.login;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.juls.model.Good;
import com.juls.model.User;
import com.juls.persist.GoodDAOImpl;
import com.juls.rest.services.GoodsService;

@Controller
@RequestMapping(value="/goods")
@Scope("request")
public class GoodsController {

	
	@Autowired Good selected;
	
	@RequestMapping(value="/all", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Good> getAllGoods(HttpSession session){
		List<Good> resultList =  new GoodDAOImpl().getAll();
		
		System.out.println("--------------");
		if(null != session){
			for (Enumeration<String> e = session.getAttributeNames(); e.hasMoreElements();) {
				String attrName = e.nextElement();
				Object attrObject = session.getAttribute(attrName);
				System.out.println("attribute = " + attrName);
				System.out.println("attrValue = " + attrObject);
				if(attrName.equals("user") && attrObject != null) {
					User user = (User)attrObject;
					System.out.println("user.email = " + user.getEmail());
				}
				if(attrName.equals("good") && attrObject != null) {
					Good good = (Good)attrObject;
					System.out.println("good.name = " + good.getName());
					System.out.println("good.id = " + good.getId());
				}
			}
		}
		System.out.println("--------------");
		
		return resultList;
	}
	
	@RequestMapping(value="/get/{goodId}", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody Good get(@PathVariable("goodId")String goodId){
		if (!goodId.isEmpty()){
			Good good = new GoodDAOImpl().getById(goodId);
			if (null != good){
				selected.setId(good.getId());
				selected.setName(good.getName());
				selected.setPrice(good.getPrice());
				selected.setGoodInfo(good.getGoodInfo());
			}
			return good;
		}
		return null;
	}
	
	@RequestMapping(value="/chosen", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody Good getChosen(){
		return selected;
	}
	
	@RequestMapping(value= "/search/{query}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody List<Good>searchForGood(@PathVariable("query") String query){
		GoodsService gsrvc = new GoodsService();
		return gsrvc.getSearchResult(query.toLowerCase());
	}
}
