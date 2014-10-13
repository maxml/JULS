$('#buyBtn').on('click', 'button.btn-add-to-cart', function() {
    var itemId = $(this).attr('data-item-id');
    put(itemId);
});

$('#goodsList').on('click', 'button.btn-add-to-cart', function() {
    var itemId = $(this).attr('data-item-id');
    put(itemId);
});

function put(itemId){
	$.ajax({
    	type: "PUT",
    	async: true,
    	contentType: "x-www-form-urlencoded; charset=UTF-8",
    	url: "../../cart/put/"+ itemId //?itemUUID=" + itemId
    }).done(function( responseData ) {
    	var feedbackForUser;
    	if(responseData["code"] == 1)
    		feedbackForUser = "Please, Sign In or Sign Up.";
    	else if(responseData["code"] == 2)
    		feedbackForUser = "Unknown item.";
    	else if(responseData["code"] == 3)
    		feedbackForUser = "New item was added to cart.";
    	else if(responseData["code"] == 4)
    		feedbackForUser = "This item is in cart already.";
    		
    	alert(feedbackForUser);
    });
};

$(document).ready(function(){
	var btnName = "btn-add-to-cart";
	$.ajax({
		type: 'GET',
		url: "../../goods/chosen",
		async: true,
		contentType: "x-www-form-urlencoded; charset=UTF-8"
	}).done(function(response) {
		$('#buyBtn').html("<button id='" + btnName + "' data-item-id='" + response["id"] + "' class='" + btnName + "'>Buy</button>");
	});
});

