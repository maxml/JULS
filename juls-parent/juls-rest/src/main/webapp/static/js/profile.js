$(document).ready(function(){
	$.ajax({
		type : 'GET',
		async: true,
		url: '../../user/curr',
		contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
		success: function(answer){
			$('#oldEmail').prop("value", answer["email"]);
			var userDetails = answer["additionalInfo"];
			if (userDetails){	
				$('#fName').prop("value", userDetails["firstName"]);
				$('#lName').prop("value", userDetails["lastName"]);
				$('#address').prop("value", userDetails["address"]);
				$('#pNumber').prop("value", userDetails["mobilePhoneNumber"]);
			}
		}
	});
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
				
			}
		}
	});
});

function createEditUserDetailsButton() {
	$('#cabinet-container').html('<button id="btn-edit-user"> Edit </button>');
	$('#cabinet-container').on('click', '#btn-edit-user', function() {
		window.location = "/cabinet.html";
	});
}

