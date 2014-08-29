$(document).ready(function(){
	$.ajax({
		type : 'GET',
		async: true,
		url: '../../user/curr',
		contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
		success: function(answer){
			$('#userCabinetLink').html(cutEmailToUserName(answer["email"]));
		}
	});
});

function cutEmailToUserName(email){
	var atIndex = email.indexOf('@');
	return email.substring(0, atIndex);
};