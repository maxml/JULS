$(document).ready(function(){
	createSignOutButton();
	$.ajax({
		type : 'GET',
		async: true,
		url: '../../user/curr',
		contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
		success: function(answer){
			
			$('#userCabinetLink').html(cutEmailToUserName(answer["email"]));
		}
	});
	if (window.localStorage.getItem("category")){
		$('#searchquery').val("");
		lastCategory = window.localStorage.getItem("category");
		$.ajax({
			type:'GET',
			url: '../../goods/byCat/' + lastCategory,
			async: true,
			contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
			success:function(answer){
				showGoods(answer);
				window.localStorage.removeItem("category");
			}
		});
	}
});


$('.mainHeader').click(function(){
	window.location = "main.html";
});

var lastCategory = "all";

$('.categories').click(function(){
	$('#searchquery').val("");
	lastCategory = $(this).text();
	$.ajax({
		type:'GET',
		url: '../../goods/byCat/' + $(this).text(),
		async: true,
		contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
		success:function(answer){
			showGoods(answer);
		}
	});
});


$('.catmenu ul').click(function(event){
	event.stopPropagation();
}).on('click', '.catmenuitem', function(event){
	event.stopPropagation();
	var itemId = $(this).attr("data-item-id");
	ShowSelectedGood(itemId);
});

var next;

$('.catmenu').click(function(){
	var clickCount = $(this).attr("data-clicks");
	next = $(this).children("ul");
	if (clickCount === "0"){
		$(this).attr("data-clicks", "1"); //increments clicks count
	}
	else if (clickCount === "1"){
		$(this).attr("data-clicks", "2"); //increments clicks count
	}
	
	if ($(this).attr("data-clicks") !== "2"){
		$.ajax({
			type:'GET',
			url: '../../goods/byCat/' + $(this).text(),
			async: true,
			contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
			success:function(answer){
				for (var i = 0; i < answer.length; i++){
					next.append('<li class="catmenuitem" data-item-id="' + answer[i]["id"] + '">' + answer[i]["name"] + "</li>");
				}
				
			}
		});
	}
	else{
		$(this).attr("data-clicks", "0");
		next.empty();
	}
});

var arrowDownSign = "%u2191";
var arrowUpSign = "%u2193";

$('#sortBy').change(function(){
	if (lastCategory !== "all")
		$('#searchquery').val("");
	var comboBoxValue = $('#sortBy option:selected').text();
	var searchQuery = $('#searchquery').val();
	if (searchQuery.trim() === ''){
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
		url: '../../goods/sort?query=' + searchQuery + '&by=' + by + '&direction=' + way + '&category=' + lastCategory + '',
		contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
		success: function(answer){
			showGoods(answer);
		}
	});
}

$('#searchBtn').click(function(){
	lastCategory = "all";
	var searchQuery;
	if ($('#searchquery').val() === "search"){
		searchQuery = "";
	}
	else{
		searchQuery = $('#searchquery').val();
	}
	$.ajax({
		type: 'GET',
		async: true,
		url: '../../goods/search/'+ searchQuery,
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