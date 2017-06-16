
$( document ).ready( function () {
	$( "#login-form" ).on( "submit", loginFormSubmit );
} );

function loginFormSubmit( event ) {
	event.stopPropagation();
	event.preventDefault();
	if($('#username').val() == ""){
		$( ".err-msg1").css('visibility','visible');
		return false;
	}
	post();
}

function vildate(){
    var username=$('#username').val();
    if( username == ""){
		$( ".err-msg1").css('visibility','visible');
		return false;
	}else{
		$( ".err-msg1").css('visibility','hidden');
	}
}
$('#username').on('input',function(){
    vildate();
});

function post() {
	
	var data = {
		username: $( "#username" ).val(),
		password: $( "#password" ).val(),
		referer: $( "#referer" ).val()
	};
	$.ajax( {
		url: "/cas/user/login",
		method: "POST",
		contentType: "application/json",
		data: $.toJSON(data),
		dataType: "json",
		success: function ( response ) {
			if ( response.statusCode === 200 ) {
				window.location.href = response.header.referer;
			} else {
				/*alert( response.message );*/
				$( ".err-msg2").css('visibility','visible');
			}
		},
		error: function ( msg ) {
			console.log( msg );
		}
	} );
}
