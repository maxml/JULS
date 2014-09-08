$(document).ready(function(){
	$.ajax({
		type: 'GET',
		url: '../../goods/all',
		async: true,
		contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
		success: function(receivedJSON){
			showGoods(receivedJSON);
		}
	});
});

function showGoods(JSON){
	var HTMLString = "";
	var moreInfoBtnName = "btn-more-info";
	var addToCartBtnName = "btn-add-to-cart";
	for (var i = 0; i < JSON.length; i++){
		var object = JSON[i];
		HTMLString += "<li>"+ object["name"] + " " + object["price"]
			+ " <button id='" + moreInfoBtnName + "-" + i + "' data-item-id='" + object["id"] + "' class='" + moreInfoBtnName + "'>More info</button>"
			+ " <button id='" + addToCartBtnName + "-" + i + "' data-item-id='" + object["id"] + "' class='" + addToCartBtnName + "'>Add to cart</button>"
					   + "</li>";
	}
	$('#goodsList').html(HTMLString);
}