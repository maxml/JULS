package com.juls.rest.dto;

import java.util.List;

public class CartStateDeltaDTO {
	
	private String cartId;
	private List<GoodDeltaDTO> goodDelta;
	
	public String getCartId() {
		return cartId;
	}
	public void setCartId(String cartId) {
		this.cartId = cartId;
	}
	public List<GoodDeltaDTO> getGoodDelta() {
		return goodDelta;
	}
	public void setGoodDelta(List<GoodDeltaDTO> goodDelta) {
		this.goodDelta = goodDelta;
	}
}
