$(document).ready(function(){
	showItemsInOrder();
	showDefaultOrderDetails();
});

function showItemsInOrder() {
	$.ajax({
		type: 'GET',
		url: "../../cart/all",
		async: true,
		contentType: "x-www-form-urlencoded; charset=UTF-8"
	}).done(function(response) {
		if(response.length == 0)
			addHtmlForEmptyCart();
		else {
			createEmptyTable();
			addItemsOnPage(response);
		}
	});

}

function createEmptyTable() {
	$("#item-container").html("<table><thead><tr><td><b>Name</b></td>" + 
			"<td><b>Price per one</b></td><td><b>Amount</b></td></tr></thead>" + 
			"<tfoot><tr><td colspan='3' id='total-price'></tr></tfoot>" + 
			"<tbody></tbody></table>");
}

function addItemsOnPage(data) {
	addCartIdAttribute(data);
	createEmptyTable();
	var goods = data["goods"];
	for (var i = 0; i < goods.length; i++) {
		addRow(goods[i], i);
	}
	getTotalPrice(data["id"]);
}

function addCartIdAttribute(data) {
	$("#item-container").attr("data-cart-id", data["id"]);
}

function addRow(item, number) {
	$("#item-container tbody").append("<tr id='row-item-" + number + "'><td>" + item["name"] + 
			"</td><td>$ " + item["price"] + 
			"</td><td>" + item["amount"] + "</td></tr>");
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

function showDefaultOrderDetails() {
	$.ajax({
		type : 'GET',
		async: true,
		url: '../../user/curr',
		contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
		success: function(answer){
				$('#fName').prop("value", answer["firstName"]);
				$('#lName').prop("value", answer["lastName"]);
				$('#address').prop("value", answer["address"]);
				$('#pNumber').prop("value", answer["mobilePhoneNumber"]);
		}
	});
}