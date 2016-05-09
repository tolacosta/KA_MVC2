function toggle(source) {
  checkboxes = document.getElementsById('select-all');
  for(var i=0, n=checkboxes.length;i<n;i++) {
    checkboxes[i].checked = source.checked;
  }
}

function sentDTO(userid,page) {
	var typeid = document.getElementById("valtype"+userid+"").value;
	alert(typeid);
		$.post("adminupdateusertype.act?userid="+userid+"&typeid="+typeid+"&page="+page
			,function(data){
			$.post("adminlistalluser.act",{page:page},function(data){
				$("#getUser").html(getUserData(data));
			});			
		});
}

function gotopage(url){
	$.get(url+"&tfsearch="+$("#search").val()+"&setNumrow="+$("#setNumrow").val()
		,function(data){
		$("#getUser").html(getUserData(data));					
	});
}
function getUserData(data){
	
	var user = data.adminListUser;
	var usertype = data.usertype;
	var divuser = "<div class='table-responsive'>" +
					   "<table class='table table-th-block table-hover'>" +
					   "<thead>" +
						   "<tr>" +
							   "<th style='width: 30px;'>No</th>" +
							   "<th style='width: 30px;'></th>" +
							   "<th>Username</th>" +
							   "<th>Email</th>" +
							   "<th>Gender</th>" +
							   "<th>Phone Number</th>" +
							   "<th>Registered Date</th>" +
							   "<th>User Type</th>" +
						   "</tr>" +
					   "</thead>" +
					   "<tbody>";
	var pagin = data.pagination;
	var pagination = "<div class='row'><div class='col-sm-6'><div class='dataTables_info' id='datatable-example_info'>Page "+ pagin.currentPage +" of "+ data.totalpages +" entries</div></div><div class='col-sm-6'><div class='dataTables_paginate paging_bootstrap'>"+
						"<ul class='pagination separated'>";
	var result = "";
	
	if(user.length != 0){
		for(var i=0;i<user.length;i++){   
			var usertypeid = user[i].usertypeid;
			var opt =   ""; 
			
			if(usertype.length != 0){
				for( j = 0 ; j< usertype.length ; j++){
					if(user[i].usertypeid == usertype[j].usertypeid){
						opt += "<option selected value='"+ usertype[j].usertypeid +"'>"+  usertype[j].usertypename +"</option>";
						console.log(opt + " & " + user[i].username +"\n");
					}else{
						opt += "<option  value='"+ usertype[j].usertypeid +"'>"+  usertype[j].usertypename +"</option>";
						//console.log(opt);
					}
				}
			}
			
//			if(usertypeid  == "1"){
//				opt = "<option value='1' >Visiter</option>" +
//					  "<option value='2' selected>Subscriber</option>" +
//					  "<option value='3'>Editor</option>";
//				      "<option value='4'>Moderator</option>";
//				      "<option value='5'>Admin</option>";
//			}else if(usertypeid  == "2"){
//				opt = "<option value='1' >Visiter</option>" +
//				  "<option value='2' selected>Subscriber</option>" +
//				  "<option value='3'>Editor</option>";
//			      "<option value='4'>Moderator</option>";
//			      "<option value='5'>Admin</option>";
//			}else if(usertypeid  == "3"){
//				opt = "<option value='1' >Visiter</option>" +
//				  "<option value='2' selected>Subscriber</option>" +
//				  "<option value='3'>Editor</option>";
//			      "<option value='4'>Moderator</option>";
//			      "<option value='5'>Admin</option>";
//			}else if(usertypeid  == "4"){
//				opt = "<option value='1' >Visiter</option>" +
//				  "<option value='2' selected>Subscriber</option>" +
//				  "<option value='3'>Editor</option>";
//			      "<option value='4'>Moderator</option>";
//			      "<option value='5'>Admin</option>";
//			}else{
//				opt = "<option value='1' >Visiter</option>" +
//				  "<option value='2' selected>Subscriber</option>" +
//				  "<option value='3'>Editor</option>";
//			      "<option value='4'>Moderator</option>";
//			      "<option value='5'>Admin</option>";
//			}
			
			divuser += "<tr>" +
						"<td>"+parseInt((pagin.currentPage-1)*pagin.perPage+i+1)+"</td>" +
						"<td><input type='checkbox'></td>" +
						"<td><img src='../uploads/"+user[i].userimageurl+"' class='avatar img-circle' alt='avatar'>"+user[i].username+"</td>" +
						"<td>"+user[i].email+"</td>" + 
						"<td>"+user[i].gender+"</td>" +
						"<td>"+user[i].phonenumber+"</td>" +
						"<td>" +
						user[i].registerdate +
						"<!-- <a href='#'><i class='fa fa fa-eye icon-rounded icon-xs icon-info'></i></a> -->" +
						"</td>" +
						"<td style='width: 140px;'>" +
							"<select id='valtype"+ user[i].userid +"' onchange=sentDTO("+user[i].userid+","+pagin.currentPage+") class='form-control' tabindex='2'>" +
								opt+
							 "</select>" +
						"</td>" +
						"</tr>";
		}
		
		divuser +=			"</tbody>" +
						"</table>" +
					"</div>" +
					"<!-- /.table-responsive -->";

			
			if(data.hasPrevious == true)
				pagination += "<li><a href='#pre="+data.previouspage +"' onclick=gotopage('adminlistalluser.act?page="+data.previouspage +"') >Previous</a></li>";
			if(pagin.currentPage != 1)
				pagination += "<li><a href='#page=1' onclick=gotopage('adminlistalluser.act?page="+1 +"')>1</a></li>";   
			var pagenum =  0;
			for(var i=pagin.currentPage; i<=data.totalpages; i++){
				if(i==pagin.currentPage){
					pagination += "<li class='active'><a href='#page="+i+"'  onclick=gotopage('adminlistalluser.act?page="+i +"')>"+i+"</a></li>";
				}else{
					pagination += "<li><a href='#page="+i+"' onclick=gotopage('adminlistalluser.act?page="+ i +"')>"+i+"</a></li>";
				}
				if(pagin.currentPage==1){
					if(i>=pagin.currentPage+4 ){
						break;
					}
				}else{
					if(i>=pagin.currentPage+4 ){
						break;
					}	
				}
			}
			if(data.hasNext == true)
				pagination += "<li><a href='#next' onclick=gotopage('adminlistalluser.act?page="+data.nextpage +"')>Next</a></li>";
			if(data.totalpages!=pagin.currentPage){
				pagination += "<li><a href='#page="+data.totalpages+"' onclick=gotopage('adminlistalluser.act?page="+data.totalpages +"')>Last</a></li>";
			}		
					
			pagination += "</div></div></div>";
			
			result = divuser += pagination;
			
			}else{
				result = "<div class='alert alert-success fade in alert-dismissable'>"+
				 			"<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>x</button>"+
				 			"<strong>No</strong> users!"+ 
				 	     "</div>";
			}
	
			return result;
	
	
}