$(document).ready(function(){
	$.ajax({
		type : 'GET',
		async: true,
		url: '../../user/curr',
		contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
		success: function(answer){
			if (answer["regStatus"] == 0)
				$('#accStat').html('Account status: <font color="red">Unconfirmed</font>');
			else
				$('#accStat').html('Account status: <font color="green">Registered</font>')
			$('#userCabinetLink').html(cutEmailToUserName(answer["email"]));
		}
	});
});

function cutEmailToUserName(email){
	var atIndex = email.indexOf('@');
	return email.substring(0, atIndex);
};