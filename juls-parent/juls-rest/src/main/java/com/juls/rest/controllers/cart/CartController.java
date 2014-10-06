package com.juls.rest.controllers.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.juls.model.User;
import com.juls.rest.dto.CartItemDTO;
import com.juls.rest.dto.CartStateDeltaDTO;
import com.juls.rest.services.CartService;
import com.juls.rest.services.CartStateService;

@Controller
@RequestMapping(value = "/cart")
@Scope("request")
public class CartController {
	
	@Autowired
	User currentUser;
	
	@RequestMapping(value = "/put/{itemUUID}", method = RequestMethod.PUT, produces = "application/json")
	public @ResponseBody String addToCart(@PathVariable("itemUUID") String itemUUID) {

		int answerCode = new CartService().addToCart(currentUser, itemUUID);
		
		return "{\"code\":" + answerCode + "}";
	}
	
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String showAllItems() {
		return new CartService().getAllGoods(currentUser);
	}
	
	@RequestMapping(value = "/change", method = RequestMethod.POST, 
			produces = "application/json", consumes = "application/json")
	public @ResponseBody String changeGoodAmount(@RequestBody CartStateDeltaDTO cartDelta) {
		CartStateService cartStateService = new CartStateService();
		return "{\"answer\":" + cartStateService.changeGoodsAmount(cartDelta) + "}";
	}
	
	@RequestMapping(value = "/item/delete", method = RequestMethod.POST, 
			produces = "application/json", consumes = "application/json")
	public @ResponseBody String deleteItemFromCart(@RequestBody CartItemDTO cartItem) {
		CartStateService cartStateService = new CartStateService();
		return "{\"answer\":" + cartStateService.deleteItem(cartItem) + "}";
	}
}
