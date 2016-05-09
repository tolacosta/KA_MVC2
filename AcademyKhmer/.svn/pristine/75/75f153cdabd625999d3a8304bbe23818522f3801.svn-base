function getJsonVideos(data){
	var videos = data;

	var table = "<table class='table table-th-block table-hover'>" +
					"<thead>" +
						"<tr>" +
							"<th>No</th>" +
							"<th>Video name</th>" +
							"<th>Public view</th>" +
							"<th>Post date</th>" +
							"<th>User</th>" +
							"<th>View count</th>" +
							"<th>Action</th>" +
						"</tr>" +
					"</thead>";

	if(videos.length != 0){
		table += "<tbody>";
		for(var i=0; i < videos.length; i++){
					 table += "<tr>" +
					 			"<td>1</td>"+
								"<td>"+ videos[i].videoname +"</td>" +
								"<td>"+ videos[i].publicview +"</td>" +
								"<td>"+ videos[i].postdate +"</td>" +
								"<td>"+ videos[i].username +"</td>" +
								"<td>"+ videos[i].viewcounts +"</td>" +
								"<td>" +
									"<button class='btn btn-danger' onclick=deletedata('deletevideopro.act?txtvideoid="+ videos[i].videoid +"') style='margin-right:3px' >Delete</button>" +
									"<button class='btn btn-primary' onclick=window.location.href='updatevideo.act?txtvideoid="+ videos[i].videoid +"' >Update</button>" +
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
			$("#getvideos").html(getJsonVideoss(data));
		});
	} 
}