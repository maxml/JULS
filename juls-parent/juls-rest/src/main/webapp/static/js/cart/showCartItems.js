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
	$("#item-container").html("<ul></ul>");
	for (var i = 0; i < data.length; i++) {
		$("#item-container ul").append("<li><b>Name:</b> " + data[i]["name"] + "  <b>Price:</b> " + data[i]["price"] + "</li>");
	}
}