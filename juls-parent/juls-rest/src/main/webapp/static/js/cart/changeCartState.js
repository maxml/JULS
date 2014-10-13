$('#item-container').on('click', 'button#btn-change-cart-state', function() {
	var currentValue = $('#itemsCount').val();
	if (currentValue.indexOf("-") == -1){
		sendNewCartState();
	}
});

$('#item-container').on('click', 'button.btn-delete-item-from-cart', function() {
	deleteItem($(this).attr('data-row-id'), $(this).attr('data-item-id'));
	deleteTableIfEmpty();
});

$('#item-container').on('click', 'button#btn-buy', function() {
	$.ajax({
		type: 'POST',
		url: "/cart/order",
		async: true,
		success: window.location = 'order.html',
		contentType: "x-www-form-urlencoded; charset=UTF-8"
	});
});

function deleteItem(rowId, itemId) {
	var item = new Object();
	item.cartId = $("#item-container").attr("data-cart-id");
	item.itemId = itemId;
	
	$.ajax({
	    type: "post",
	    url: "/cart/item/delete", 
	    contentType: "application/json", 
	    data: JSON.stringify(item), 
	    success: function(result) {
	        if(result["answer"] === 0) {
	        	deleteTableRow(rowId);
	        	getTotalPrice(item["cartId"]);
	        }
	    }
	});
}

function deleteTableRow(rowId) {
	$('#' + rowId).remove();
	if($.trim($('tbody').html()) == '') {
		$('#item-container').empty();
		$('#item-container').text('Cart is empty');
	}
}

function sendNewCartState() {
	var cartState = formCartState();
	var jsonString = JSON.stringify(cartState);
	
	$.ajax({
	    type: "post",
	    url: "/cart/change", 
	    contentType: "application/json", 
	    data: jsonString, 
	    success: function(result) {
	        getTotalPrice(cartState["cartId"]);
	    }    
	});
}

function formCartState() {
	var cartState = new Object();
	cartState.cartId = $("#item-container").attr("data-cart-id");
	cartState.goodDelta = [];
	
	$('tbody tr').each(function() {
		var itemState = new Object();
		var inputTag = $(this).find('input');
		itemState.goodId = inputTag.attr('data-item-id');
		itemState.goodAmount = inputTag.val();
		cartState.goodDelta.push(itemState);
	});
	return cartState;
}