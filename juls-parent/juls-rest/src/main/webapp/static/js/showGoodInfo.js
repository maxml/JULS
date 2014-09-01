$(document).ready(function(){
	$.ajax({
		type: 'GET',
		url: "../../goods/chosen",
		async: true,
		contentType: "x-www-form-urlencoded; charset=UTF-8"
	}).done(function(response) {
		$('#goodName').text(response["name"]);
		$('#price').text(response["price"]);
	});
});
