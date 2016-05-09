/**
 * 
 */

			$("#changepwdvalidator").submit(function(){
				
				$.post("changeuserpassword.act", {
					myoldpwd : $("#choldpwd").val(),
					mynewpwd : $("#chpassword").val()
				}, 
				function(data){
					if(data=="Success"){
						location.reload();
					}else{
						$("#cantchangepwd").text("Invalid Password! Please check your old or new password!");
					}
				});
			});
			
			
			
			function checkcholdpwd(){
				
			$.post("checkoldpassword.act" ,
					{
				
				oldpassword : $("#choldpwd").val()
				
					},
					function(data){
						if(data=="Success"){
							$("#choldvalid").text("");
							//$("#chsubmit").attr("type", "submit");								
							
						}else{
							
							$("#choldpwd").val("");
							$("#choldvalid").text("Password Not Valid. Please Entry Again!");
							$("#chsubmit").attr('disabled','disabled');
							
						}
					}
			
			);				
		}
		