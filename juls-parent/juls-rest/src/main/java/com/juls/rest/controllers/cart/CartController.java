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
import com.juls.rest.dto.*;
import com.juls.rest.services.CartService;
import com.juls.rest.services.CartStateService;
import com.juls.rest.services.Redirector;

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
		int answer = cartStateService.changeGoodsAmount(cartDelta);
		cartStateService.calculateTotalPrice(currentUser, cartDelta.getCartId());
		return "{\"answer\":" + answer + "}";
	}
	
	@RequestMapping(value = "/item/delete", method = RequestMethod.POST, 
			produces = "application/json", consumes = "application/json")
	public @ResponseBody String deleteItemFromCart(@RequestBody CartItemDTO cartItem) {
		CartStateService cartStateService = new CartStateService();
		int answer = cartStateService.deleteItem(cartItem);
		cartStateService.calculateTotalPrice(currentUser, cartItem.getCartId());
		return "{\"answer\":" + answer + "}";
	}
	
	@RequestMapping(value = "{cartUUID}/price", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String getTotalPrice(@PathVariable("cartUUID") String cartUUID) {
		CartStateService cartStateService = new CartStateService();
		System.out.println("cartUUID = " + cartUUID);
		return "{\"totalPrice\":" + cartStateService.getTotalPrice(cartUUID) + "}";
	}

	
	/* Creates order of current User and put current curt inside.
	 * 
	 *  Redirecting to .../order.html	 */
	@RequestMapping(value = "/order", method = RequestMethod.POST,
			headers = "Accept=*/*", produces = "application/json")
	public String createOrder() {
		if(new CartService().createOrder(currentUser)) {
			return "{order.html}";
		}
		else {
			return "Failed to create order!";
		}
	}
}
