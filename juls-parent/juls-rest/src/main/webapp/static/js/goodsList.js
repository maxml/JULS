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
	for (var i = 0; i < JSON.length; i++){
		var object = JSON[i];
		HTMLString += "<li><strong>ID:</strong> "+object["id"] + " <strong>NAME:</strong> " + object["name"] + " <strong>PRICE:</strong> " + object["price"]+"</li>";
		
	}
	$('#goodsList').html(HTMLString);
}