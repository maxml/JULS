$('#goodsList').on('click', 'button.btn-add-to-cart', function() {
    var itemId = $(this).attr('data-item-id');
    $.ajax({
    	type: "PUT",
    	url: "/cart/put/" + itemId 
    }).done(function( responseData ) {
    	alert(responseData);
    });
});