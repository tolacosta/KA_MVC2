function getJsonMainCategories(data){
	var maincategories = data;

	var table = "<table class='table table-th-block table-hover'>" +
					"<thead>" +
						"<tr>" +
							"<th>Main category name</th>" +
							"<th>Main category logo url</th>" +
							"<th>Main category order</th>" +
							"<th>Action</th>" +
						"</tr>" +
					"</thead>";

	if(maincategories.length != 0){
		table += "<tbody>";
		for(var i=0;i<maincategories.length;i++){
					 table += "<tr>" +
								"<td>"+ maincategories[i].maincategoryname +"</td>" +
								"<td>"+ maincategories[i].maincategorylogourl +"</td>" +
								"<td>"+ maincategories[i].maincategoryorder +"</td>" +
								"<td>" +
									"<button class='btn btn-danger' onclick=deletedata('deletemaincategorypro.act?txtmaincategoryid="+ maincategories[i].maincategoryid +"') style='margin-right:3px' >Delete</button>" +
									"<button class='btn btn-primary' onclick=window.location.href='updatemaincategory.act?txtmaincategoryid="+ maincategories[i].maincategoryid +"' >Update</button>" +
								"</td>" +
							  "</tr>";
		}
		table += "</tbody>";
	}
		
	return table += "</table>";
}

function deletedata(url){
	if (confirm("Are you sure you want to delete?") == true) {
		$.get(url ,function(data){
			$("#getmaincategories").html(getJsonMainCategories(data));
		});
	} 
}