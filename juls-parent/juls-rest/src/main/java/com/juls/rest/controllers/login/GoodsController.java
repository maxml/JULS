package com.juls.rest.controllers.login;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.juls.model.Good;
import com.juls.persist.GoodDAOImpl;

@Controller
@RequestMapping(value="/goods")
public class GoodsController {

	@RequestMapping(value="/all", method = RequestMethod.GET, headers = "Accept=*/*",produces = "application/json")
	public @ResponseBody List<Good> getAllGoods(){
		List<Good> resultList =  new GoodDAOImpl().getAll();
		return resultList;
	}
}
