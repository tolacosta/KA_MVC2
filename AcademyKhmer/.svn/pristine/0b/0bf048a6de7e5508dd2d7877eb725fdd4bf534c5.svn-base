function getJsonCategories(data){
	var categories = data.categorylist;
	
	var table = "<table class='table table-th-block table-hover'>" +
					"<thead>" +
						"<tr>" +
							"<th>Main Category Name</th>" +
							"<th>Category Name</th>" +
							"<th>Category Logo URL</th>" +
							"<th>Number Of Videos</th>" +
							"<th>Action</th>" +
						"</tr>" +
					"</thead>";
	
	var pagin = data.pagination;
	var pagination = "<div class='row'><div class='col-sm-6'><div class='dataTables_info' id='datatable-example_info'>Page "+ pagin.currentPage +" of "+ data.totalpages +" entries</div></div><div class='col-sm-6'><div class='dataTables_paginate paging_bootstrap'>";
	pagination += "<ul class='pagination separated'>";

	if(categories.length != 0){
		table += "<tbody>";
		for(var i=0;i<categories.length;i++){
					 table += "<tr>" +
								"<td>"+ categories[i].maincategoryname +"</td>" +
								"<td>"+ categories[i].categoryname +"</td>" +
								"<td>"+ categories[i].categorylogourl +"</td>" +
								"<td>"+ categories[i].countvideos +"</td>" +
								"<td>" +
									"<button class='btn btn-danger' onclick=deletedata('deletecategorypro.act?categoryid="+ categories[i].categoryid +"') style='margin-right:3px' >Delete</button>" +
									"<button class='btn btn-primary' onclick=window.location.href='updatecategory.act?categoryid="+ categories[i].categoryid +"' >Update</button>" +
								"</td>" +
							  "</tr>";
		}
		table += "</tbody></table>";
		
		
		//pagination
		
		
		if(data.hasPrevious == true)
			pagination += "<li><a href='#pre="+data.previouspage +"' onclick=gotopage('listcategorypro.act?page="+data.previouspage +"') >Previous</a></li>";
		if(pagin.currentPage != 1)
			pagination += "<li><a href='#page=1' onclick=gotopage('listcategorypro.act?page="+1 +"')>1</a></li>";   
		var pagenum =  0;
		for(var i=pagin.currentPage; i<=data.totalpages; i++){
			if(i==pagin.currentPage){
				pagination += "<li class='active'><a href='#page="+i+"'  onclick=gotopage('listcategorypro.act?page="+i +"')>"+i+"</a></li>";
			}else{
				pagination += "<li><a href='#page="+i+"' onclick=gotopage('listcategorypro.act?page="+ i +"')>"+i+"</a></li>";
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
			pagination += "<li><a href='#next' onclick=gotopage('listcategorypro.act?page="+data.nextpage +"')>Next</a></li>";
		if(data.totalpages!=pagin.currentPage){
			pagination += "<li><a href='#page="+data.totalpages+"' onclick=gotopage('listcategorypro.act?page="+data.totalpages +"')>Last</a></li>";
		}		
				
		pagination += "</div></div></div>";

	}
	
	
	
	
	
	return table+pagination;
}

function gotopage(url){
	$.get(url+"&setNumrow="+$("#setNumrow").val()
		,function(data){
		$("#getcategories").html(getJsonCategories(data));					
	});
}

function deletedata(url){
	if (confirm("Are you sure you want to delete?") == true) {
		$.get(url ,function(data){
			$("#getcategories").html(getJsonCategories(data));
		});
	} 
}