$("#btn-sign-out").click(function () {
	$.ajax({
		type: 'GET',
		url: "/signout",
		async: true,
		contentType: "x-www-form-urlencoded; charset=UTF-8"
	}).done(function(response) {
		alert(response["link"]);
		$(location).attr('href', response["link"]);
		
	});
});