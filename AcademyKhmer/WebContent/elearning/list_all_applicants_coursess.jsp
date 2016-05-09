<%@page import="model.dto.ShortCourse"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="model.dto.User"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
 <%@page import="Controller.Common_method"%>
<%@page import="model.dto.University"%>
<%@page import="model.dto.Department"%>
<%@page import="java.util.ArrayList"%>
<%
		User usr= new User();
		if( session.getAttribute("ka_user") == null){
			out.println("<script>window.location.href='shortcourse.act'; </script>");return;
		}else{
			usr = (User) session.getAttribute("ka_user");
			if(usr.getUsertypeid() != 5){
				out.println("<script>window.location.href='shortcourse.act'; </script>");return;
			}	
		}
			
	 %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<meta name="description" content="We share Knowledge with IT lessons, IT Traing to students. Enjoy with Khmer Academy!">
		<meta name="keywords" content="Khmer Academy, Khmer Learning Online, Khmer IT Study , IT Traning in Cambodia, Khmer Forum, IT Chatting">
		<meta name="author" content="Khmer Academy">
		<title>Precourse Information | Khmer Acedemy</title>
 
		<!-- BOOTSTRAP CSS (REQUIRED ALL PAGE)-->
		<link href="assets/css/bootstrap.min.css" rel="stylesheet">
		
		<!-- PLUGINS CSS -->
		<link href="assets/plugins/weather-icon/css/weather-icons.min.css" rel="stylesheet">
		<link href="assets/plugins/prettify/prettify.min.css" rel="stylesheet">
		<link href="assets/plugins/magnific-popup/magnific-popup.min.css" rel="stylesheet">
		<link href="assets/plugins/owl-carousel/owl.carousel.min.css" rel="stylesheet">
		<link href="assets/plugins/owl-carousel/owl.theme.min.css" rel="stylesheet">
		<link href="assets/plugins/owl-carousel/owl.transitions.min.css" rel="stylesheet">
		<link href="assets/plugins/chosen/chosen.min.css" rel="stylesheet">
		<link href="assets/plugins/icheck/skins/all.css" rel="stylesheet">
		<link href="assets/plugins/datepicker/datepicker.min.css" rel="stylesheet">
		<link href="assets/plugins/timepicker/bootstrap-timepicker.min.css" rel="stylesheet">
		<link href="assets/plugins/validator/bootstrapValidator.min.css" rel="stylesheet">
		<link href="assets/plugins/summernote/summernote.min.css" rel="stylesheet">
		<link href="assets/plugins/markdown/bootstrap-markdown.min.css" rel="stylesheet">
		<link href="assets/plugins/datatable/css/bootstrap.datatable.min.css" rel="stylesheet">
		<link href="assets/plugins/morris-chart/morris.min.css" rel="stylesheet">
		<link href="assets/plugins/c3-chart/c3.min.css" rel="stylesheet">
		<link href="assets/plugins/slider/slider.min.css" rel="stylesheet">
		
		<!-- MAIN CSS (REQUIRED ALL PAGE)-->
		<link href="assets/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet">
		<link href="assets/css/style.css" rel="stylesheet">
		<link href="assets/css/style-responsive.css" rel="stylesheet">
 
		<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
		<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
		<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
		
		<!--  CSS (REQUIRED ALL PAGE)-->
		<link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
		
		<style>
			.required{
				color: red;
			}
		</style>
	
		<SCRIPT type="text/javascript">
		
    function ValidateFileUpload() {
        var fuData = document.getElementById('photoimage');
        var FileUploadPath = fuData.value;
       // alert(FileUploadPath);
        var imageSize = fuData.files[0].size;
        var maxSize = 1024 * 1024 * 2 ;  // size 2 MB
        if(imageSize> maxSize){
        	alert("Sorry image big size please choose another.");
        	
        	//$("#imgerror").text("Sorry image sizen too big please choose another.");
        	//$("#imgerror").css("color", "red");
        }
      //  alert("This is file size of image you have to controller ....."+imageSize);
        //To check if user upload any file
        if (FileUploadPath == '') {
            alert("Please upload an image");

        } else {
            var Extension = FileUploadPath.substring(FileUploadPath.lastIndexOf('.') + 1).toLowerCase();
			//The file uploaded is an image
			if (Extension == "gif" || Extension == "png" || Extension == "gif" || Extension == "jpeg" || Extension == "jpg") {				    
                if (fuData.files && fuData.files[0]) {
                    var reader = new FileReader();
                      reader.onload = function(e) {
                         $('#photoimg').attr('src', e.target.result);
                    };
                    reader.readAsDataURL(fuData.files[0]);
                }

            } 

//The file upload is NOT an image
else {
                alert("Photo only allows file types of GIF, PNG, JPG, JPEG and BMP. ");

            }
        }
    }
</SCRIPT> 
	
	</head>
 
	<body class="tooltips">
		
		
		
		<!--
		===========================================================
		BEGIN PAGE
		===========================================================
		-->
		<div class="wrapper">
			<!-- BEGIN TOP NAV -->
			<jsp:include page="_header.jsp"></jsp:include>
			<!-- END TOP NAV -->
			
			
			
			<!-- BEGIN SIDEBAR LEFT -->
			<jsp:include page="_sidebar.jsp"></jsp:include>
			<!-- END SIDEBAR LEFT -->
			
			
			
			
			<!-- BEGIN PAGE CONTENT -->
			<div class="page-content">
				
				
				<div class="container">
					<!-- Begin page heading -->
					<!-- <h1 class="page-heading">User Information <small></small></h1> -->
					<h1></h1>
					<!-- End page heading -->
				
					<div class="row">
						
						<div class="col-sm-12">
							
									<%
										ArrayList<ShortCourse> arr = new ArrayList<ShortCourse>();
									    arr =  (ArrayList)request.getAttribute("courses");
									%>
							
							
							<div class="panel panel-primary">
								  <div class="panel-heading">
											Applicants 
								  </div>
								  
								  <div class="panel-body" style="min-height: 700px">
								  
								  	<table class="table table-th-block">
								<thead>
									<tr>
										<th>N</th>
										<th>Name</th>
										<th>Courses</th>
										<th>Time</th>
										<th>Registered Date</th>
										<th>Updated Date</th>
										<th>Phone number</th>
										<th>Email</th>
										<th>Action</th>
									</tr>
								</thead>
								<tbody>
									
									
									<% for(int i=0;i<arr.size();i++){  %>
									<tr>
										<td><%= i+1 %></td>
										<td><%= arr.get(i).getFullname()%></td>
										<td><%=arr.get(i).getCourseName() %></td>
										<td><%=arr.get(i).getShifName() %></td>
										<td><%=arr.get(i).getRegisterDate() %></td>
										<td><%=arr.get(i).getUpdateDate() %></td>
										<td><%=arr.get(i).getPhonenumber() %></td>
										<td><%=arr.get(i).getEmail() %></td>
										<td>
											<a href="shortcourse.act?id=<%=arr.get(i).getCourseID() %>"><i class="fa fa-pencil icon-circle icon-xs icon-info"></i></a>
											<a href="delete_registerd_c.act?c_id=<%=arr.get(i).getCourseID() %>"><i class="fa fa-trash-o icon-circle icon-xs icon-danger"></i></a>  
										</td>
									</tr>
									<% } %>
								</tbody>
							</table>					
																	
									
									</div>
								  <!-- /.panel-body -->
							  <div class="panel-footer">
							  </div>
							</div>
							</div><!-- /.panel .panel-info -->
						</div>
				</div><!-- /.container-fluid -->
				
				<!-- Modal -->
				<div id="precourseevent" class="modal fade" role="dialog">
				  <div class="modal-dialog">
				
				    <!-- Modal content-->
				    <div class="modal-content">
				      <div class="modal-header">
				        <button type="button" class="close" data-dismiss="modal">&times;</button>
				        <h4 class="modal-title">Short Courses</h4>
				      </div>
				      <div class="modal-body">
				        <img class="img-responsive" src="assets/img/shortcourse_banner.png"/>
				      </div>
				    
				    </div>
				
				  </div>
				</div>
				
				<!-- BEGIN FOOTER -->
				<jsp:include page="_footer.jsp"></jsp:include>
				<!-- END FOOTER -->
				
				
			</div><!-- /.page-content -->
		</div><!-- /.wrapper -->
		<!-- END PAGE CONTENT -->
		
		
	
		
		
		
		<!--
		===========================================================
		END PAGE
		===========================================================
		-->
		
		<!--
		===========================================================
		Placed at the end of the document so the pages load faster
		===========================================================
		-->
		<!-- MAIN JAVASRCIPT (REQUIRED ALL PAGE)-->
		<script src="assets/js/jquery.min.js"></script>
		<script src="assets/js/bootstrap.min.js"></script>
		<script src="assets/plugins/retina/retina.min.js"></script>
		<script src="assets/plugins/nicescroll/jquery.nicescroll.js"></script>
		<script src="assets/plugins/slimscroll/jquery.slimscroll.min.js"></script>
		<script src="assets/plugins/backstretch/jquery.backstretch.min.js"></script>
 
		<!-- PLUGINS -->
		<script src="assets/plugins/skycons/skycons.js"></script>
		<script src="assets/plugins/prettify/prettify.js"></script>
		<script src="assets/plugins/magnific-popup/jquery.magnific-popup.min.js"></script>
		<script src="assets/plugins/owl-carousel/owl.carousel.min.js"></script>
		<script src="assets/plugins/chosen/chosen.jquery.min.js"></script>
		<script src="assets/plugins/icheck/icheck.min.js"></script>
		<script src="assets/plugins/datepicker/bootstrap-datepicker.js"></script>
		<script src="assets/plugins/timepicker/bootstrap-timepicker.js"></script>
		<script src="assets/plugins/mask/jquery.mask.min.js"></script>
		<script src="assets/plugins/validator/bootstrapValidator.min.js"></script>
		<script src="assets/plugins/datatable/js/jquery.dataTables.min.js"></script>
		<script src="assets/plugins/datatable/js/bootstrap.datatable.js"></script>
		<script src="assets/plugins/summernote/summernote.min.js"></script>
		<script src="assets/plugins/markdown/markdown.js"></script>
		<script src="assets/plugins/markdown/to-markdown.js"></script>
		<script src="assets/plugins/markdown/bootstrap-markdown.js"></script>
		<script src="assets/plugins/slider/bootstrap-slider.js"></script>
		
		<!-- EASY PIE CHART JS -->
		<script src="assets/plugins/easypie-chart/easypiechart.min.js"></script>
		<script src="assets/plugins/easypie-chart/jquery.easypiechart.min.js"></script>
		
		<!-- KNOB JS -->
		<!--[if IE]>
		<script type="text/javascript" src="assets/plugins/jquery-knob/excanvas.js"></script>
		<![endif]-->
		<script src="assets/plugins/jquery-knob/jquery.knob.js"></script>
		<script src="assets/plugins/jquery-knob/knob.js"></script>

		<!-- FLOT CHART JS -->
		<script src="assets/plugins/flot-chart/jquery.flot.js"></script>
		<script src="assets/plugins/flot-chart/jquery.flot.tooltip.js"></script>
		<script src="assets/plugins/flot-chart/jquery.flot.resize.js"></script>
		<script src="assets/plugins/flot-chart/jquery.flot.selection.js"></script>
		<script src="assets/plugins/flot-chart/jquery.flot.stack.js"></script>
		<script src="assets/plugins/flot-chart/jquery.flot.time.js"></script>

		<!-- MORRIS JS -->
		<script src="assets/plugins/morris-chart/raphael.min.js"></script>
		<script src="assets/plugins/morris-chart/morris.min.js"></script>
		
		<!-- C3 JS -->
		<script src="assets/plugins/c3-chart/d3.v3.min.js" charset="utf-8"></script>
		<script src="assets/plugins/c3-chart/c3.min.js"></script>
		
		
		<script src="assets/plugins/validator/example.js"></script>
		<script src="assets/js/myvalidator.js"></script>
		
		<!-- MAIN APPS JS -->
		<script src="assets/js/apps.js"></script>

		<script src="assets/js/close_sidebar.js"></script>
		<script src="assets/js/moment.js"></script>
		
		
		
		
		
		
	</body>
</html>