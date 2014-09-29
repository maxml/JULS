$(document).ready(function(){
	$.ajax({
		type : 'GET',
		async: true,
		url: '../../user/curr',
		contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
		success: function(answer){
			if (answer["regStatus"] == 0) {
				$('#accStat').html('Account status: <font color="red">Unconfirmed</font>');
			}
			else {
				$('#accStat').html('Account status: <font color="green">Registered</font>');
				createSignOutButton();
			}
			$('#userCabinetLink').html(cutEmailToUserName(answer["email"]));
		}
	});
});

var arrowDownSign = "%u2191";
var arrowUpSign = "%u2193";

/*<option value="price &uarr;">price &uarr;</option>
			<option value="name &uarr;">name &uarr;</option>
			<option value="price &darr;">price &darr;</option>
			<option value="name &darr;">name &darr;</option>*/

$('#sortBy').change(function(){
	var comboBoxValue = $('#sortBy option:selected').text();
	var searchQuery = $('#searchquery').val();
	if (searchQuery === ''){
		searchQuery = "null";
	}
	var sortBy;
	var sortDir;
	if (comboBoxValue.indexOf("name") > -1)
	{
		sortBy = "name";
	}
	else if (comboBoxValue.indexOf("price") > -1)
	{
		sortBy = "price";
	}
	if (comboBoxValue.indexOf(unescape(arrowDownSign)) > -1)
		sortDir = "ASC";
	else if (comboBoxValue.indexOf(unescape(arrowUpSign)) > -1)
		sortDir = "DESC";
	
	doSort(searchQuery, sortBy, sortDir);
});

function doSort(searchQuery, by, way){  
	$.ajax({
		type: "GET",
		async: true,
		url: '../../goods/sort?query=' + searchQuery + '&by=' + by + '&direction=' + way,
		contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
		success: function(answer){
			showGoods(answer);
		}
	});
}

$('#searchBtn').click(function(){
	$.ajax({
		type: 'GET',
		async: true,
		url: '../../goods/search/'+ $('#searchquery').val(),
		contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
		success: function(answer){
			showGoods(answer);
		}
	});
});

function cutEmailToUserName(email){
	if(email != null) {
		var atIndex = email.indexOf('@');
		
		createSignOutButton();
		createUserCartButton();
		
		return email.substring(0, atIndex);
		
	} else {
		createSignInButton();
	}
	
};

function createSignOutButton() {
	$('#sign-container').html('<button id="btn-sign-out">Sign Out</button>');
	$('#sign-container').on('click', '#btn-sign-out', function() {
	    signOutAJAX();
	});
}

function createSignInButton() {
	$('#sign-container').html('<button id="btn-sign-in-or-up">Sign In/Up</button>');
	$('#sign-container').on('click', '#btn-sign-in-or-up', function() {
		window.location = "/login.html";
	});
}

function createUserCartButton() {
	$('#cart-container').html('<button id="btn-user-cart">My cart</button>');
	$('#cart-container').on('click', '#btn-user-cart', function() {
		window.location = "/cart.html";
	});
}

function signOutAJAX() {
	$.ajax({
		type: 'GET',
		url: "/signout",
		async: true,
		contentType: "x-www-form-urlencoded; charset=UTF-8"
	}).done(function(response) {
		$(location).attr('href', response["link"]);		
	});
}