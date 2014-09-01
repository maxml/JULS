package com.juls.rest.controllers.login;

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
import com.juls.persist.GoodDAOImpl;

@Controller
@RequestMapping(value="/goods")
@Scope("request")
public class GoodsController {

	
	@Autowired Good selected;
	
	@RequestMapping(value="/all", method = RequestMethod.GET, headers = "Accept=*/*",produces = "application/json")
	public @ResponseBody List<Good> getAllGoods(){
		List<Good> resultList =  new GoodDAOImpl().getAll();
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
			}
			return good;
		}
		return null;
	}
	
	@RequestMapping(value="/chosen", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody Good getChosen(){
		return selected;
	}
}
