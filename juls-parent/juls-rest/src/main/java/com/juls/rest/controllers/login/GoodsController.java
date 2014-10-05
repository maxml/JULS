package com.juls.rest.controllers.login;

import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	public @ResponseBody List<Good> getAllGoods(){
		List<Good> resultList =  new GoodDAOImpl().getAll();
		return resultList;
	}
	
	@RequestMapping(value="/byCat/{category}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Good> getGoodsByCategory(@PathVariable("category")String category){
		GoodsService service = new GoodsService();
		return service.getGoodsByCategory(category);
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
	
	
	
	@RequestMapping(value="/sort", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody Set<Good> sort(@RequestParam(value="query") String query, @RequestParam(value="by") String by, @RequestParam(value="direction") String direction,
			@RequestParam(value="category")String category){
		GoodsService gService = new GoodsService();
		return gService.doSort(query, by, direction, category);
	}
	
	@RequestMapping(value="/chosen", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody Good getChosen(){
		return selected;
	}
	
	@RequestMapping(value= "/search/{query}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody Set<Good>searchForGood(@PathVariable("query") String query){
		GoodsService gsrvc = new GoodsService();
		return gsrvc.getSearchResult(query.trim());
	}
	
	@RequestMapping(value= "/search", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody Set<Good>searchForGood(){
		GoodsService gsrvc = new GoodsService();
		return gsrvc.getSearchResult("");
	}
}
