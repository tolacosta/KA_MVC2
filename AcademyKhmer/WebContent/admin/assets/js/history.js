function getJsonListExp(data){
	
	var json = data;
	
	var table = 	"<table class='table table-striped table-bordered table-hover table-checkable table-responsive datatable dataTable' id='DataTables_Table_0' aria-describedby='DataTables_Table_0_info'>" +
						"<thead> " +
							"<tr role='row'>" +
								"<th data-class='expand'   role='columnheader' tabindex='0' aria-controls='DataTables_Table_0' rowspan='1'	colspan='1'	aria-label='First Name: activate to sort column ascending'>Company Name</th>" +
								"<th data-hide='phone'   role='columnheader' tabindex='0' aria-controls='DataTables_Table_0' rowspan='1' colspan='1' aria-label='Username: activate to sort column ascending'>Position</th>" +
								"<th data-hide='phone'   role='columnheader' tabindex='0' aria-controls='DataTables_Table_0' rowspan='1' colspan='1' aria-label='Username: activate to sort column ascending'>Period</th>" +
								"<th data-hide='phone'   role='columnheader' tabindex='0' aria-controls='DataTables_Table_0' rowspan='1' colspan='1' aria-label='Username: activate to sort column ascending'>Website</th>" +
								"<th data-hide='phone'   role='columnheader' tabindex='0' aria-controls='DataTables_Table_0' rowspan='1' colspan='1' aria-label='Username: activate to sort column ascending'>Address</th>" +
								"<th data-hide='phone,tablet' role='columnheader' tabindex='0' aria-controls='DataTables_Table_0' rowspan='1' colspan='1' aria-label='Status: activate to sort column ascending'>Action </th>" +
							"</tr>" +
					"</thead>" +
					"<tbody role='alert' aria-live='polite' aria-relevant='all'>";
	
	if(json.length != 0){
		for(var i=0;i<json.length;i++){
					 table += "<tr class='odd'>" +
					 		 	"<td class=' '>" +	"<span class='responsiveExpander'></span>"+ json[i].company +"</td>" +
					 		 	"<td class=' '>"+ json[i].position +"</td>" +
					 		 	"<td class=' '>"+ json[i].period +"</td>" +
					 		 	"<td class=' '>"+ json[i].website +"</td>" +
					 		 	"<td class=' '>"+ json[i].address +"</td>" +
					 		 	"<td class='align-center'>" +
					 		 		"<span class='btn-group'>" +
					 		 			"<a href='get_experience_update.hrd?experience_id="+json[i].experience_id+"' class='bs-tooltip ' title='' data-original-title='Edit'><i class='icon-pencil'></i>&nbsp; </a>" +
					 		 			"<a onclick=deletedata('deleteexppro.hrd?experience_id="+ json[i].experience_id +"') href='#' class='bs-tooltip ' title='' data-original-title='Delete'><i class='icon-trash'></i></a>" +
					 		 		"</span>" +
					 		 "</td>" +
					 	 "</tr>";
		}
	}
					 
	return 	 table += "</tbody></table>";
	
	
}


function deletedata(url){
	if (confirm("Are you sure you want to delete this experience?") == true) {
		$.get(url
				,function(data){
			$("#getExperience").html(getJsonListExp(data));
			});
	} 

}