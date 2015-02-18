$(document).ready(function(){
	$.ajax({
		type: 'GET',
		url: "../../goods/chosen",
		async: true,
		contentType: "x-www-form-urlencoded; charset=UTF-8"
	}).done(function(response) {
		$('#goodName').text(response["name"]);
		$('#price').text(response["price"]);
		var goodInfoObject = response["goodInfo"];
		$('#description').text(goodInfoObject["description"]);
		$('#type').text(goodInfoObject["type"]);
		$('#mass').text(goodInfoObject["mass"]);
		$('#composition').text(goodInfoObject["composition"]);
	});
});

$('#type').click(function(){
	lastCategory = $('#type').text();
	window.localStorage.setItem("category", $('#type').text());
	window.location = "/main.html";
});
