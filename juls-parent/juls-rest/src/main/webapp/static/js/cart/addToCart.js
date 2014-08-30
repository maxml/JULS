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
    	alert(responseData);
    });
};