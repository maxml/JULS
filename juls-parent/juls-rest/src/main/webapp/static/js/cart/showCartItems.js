$(document).ready(function(){
	$.ajax({
		type: 'GET',
		url: "../../cart/all",
		async: true,
		contentType: "x-www-form-urlencoded; charset=UTF-8"
	}).done(function(response) {
		if(response.length == 0)
			addHtmlForEmptyCart();
		else {
			addItemsOnPage(response);
		}
	});
});

function addHtmlForEmptyCart() {
	$("#item-container").html("Cart is empty");
}

function addItemsOnPage(data) {
	addCartIdAttribute(data);
	createEmptyTable();
	var goods = data["goods"];
	for (var i = 0; i < goods.length; i++) {
		addRow(goods[i], i);
	}
	getTotalPrice(data["id"]);
	createChangeCartStateButton();
	createBuyButton();
}

function addCartIdAttribute(data) {
	$("#item-container").attr("data-cart-id", data["id"]);
}

function createEmptyTable() {
	$("#item-container").html("<table><thead><tr><td><b>Name</b></td>" + 
			"<td><b>Price per one</b></td><td><b>Amount</b></td><td><b>Delete item</b></td></tr></thead>" + 
			"<tfoot><tr><td colspan='4' id='total-price'></tr></tfoot>" + 
			"<tbody></tbody></table>");
}

function addRow(item, number) {
	$("#item-container tbody").append("<tr id='row-item-" + number + "'><td>" + item["name"] + 
			"</td><td>$ " + item["price"] + 
			"</td><td><input type='number' min='1' max='9223372036854775807' value='" + 
			item["amount"] + "' data-item-id='" + item["id"] + "'></td>" + 
			"<td><button class='btn-delete-item-from-cart' data-item-id='" + item["id"] + 
			"' data-row-id='row-item-" + number + "'>Delete</button></td></tr>");
}

function createChangeCartStateButton() {
	$("#item-container").append("<button id='btn-change-cart-state'>Change</button>");
}

function createBuyButton() {
	$("#item-container").append("<button id='btn-buy'>Buy</button>");
}

function getTotalPrice(cartId) {
	$.ajax({
		type: 'GET',
		url: "/cart/" + cartId + "/price",
		async: true,
		contentType: "x-www-form-urlencoded; charset=UTF-8"
	}).done(function(response) {
		if(response !== null || response !== undefined) {
			$('#total-price').html('');
			$('#total-price').html("Total price: <b>$ " + response["totalPrice"] + "</b>");
		}
		else {
			alert("alert!");
		}
	});
}