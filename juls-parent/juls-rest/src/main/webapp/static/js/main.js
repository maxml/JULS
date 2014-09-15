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