$('#goodsList').on('click', 'button.btn-more-info', function() {
	var itemId = $(this).attr("data-item-id");
	ShowSelectedGood(itemId);
});

/*$('.catmenuitem').click(function(event){
	event.stopPropagation();
	
});*/

function ShowSelectedGood(itemId){
	$.ajax({
		type: 'GET',
		url: "../../goods/get/" + itemId,
		async: true,
		contentType: "x-www-form-urlencoded; charset=UTF-8"
	}).done(function(response) {
		if (response)
			window.location = "selectedGood.html";
		
	});
}
