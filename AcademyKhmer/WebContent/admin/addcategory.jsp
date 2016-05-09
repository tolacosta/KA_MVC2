<!DOCTYPE html>
<%@page import="model.dto.Maincategory"%>
<%@page import="model.dto.Category"%>
<%@page import="java.util.ArrayList"%>
<% ArrayList list=(ArrayList)request.getAttribute("list_main_category"); %>
<%
	String action = "addcategory.act";
	String pageHeading = "Form Add Category";
	String buttonValue = "Add";
	Category category = null;
	if (request.getAttribute("categorydto") != null) {
		action = "updatecategorypro.act";
		pageHeading = "Form Update Category";
		buttonValue = "Save";
		category = (Category) request.getAttribute("categorydto");
	};
 %>

<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<meta name="description" content="We share Knowledge with IT lessons, IT Traing to students. Enjoy with Khmer Academy!">
		<meta name="keywords" content="Khmer Academy, Khmer Learning Online, Khmer Studying IT, IT Traning in Cambodia, Khmer Forum, IT Chatting">
		<meta name="author" content="Vuthea Chheang">
		<title>Categories | Khmer Academy</title>
 
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
				
				
				<div class="container-fluid">
					<br/>				
					<div class="panel panel-info">
							  <div class="panel-heading">
								<h3 class="panel-title">Form add category </h3>
							  </div>
							  <div class="panel-body">
									<div >
						<form  method="post" action="<%=action %>" class="form-horizontal" id="addcategory" enctype="multipart/form-data">
							<fieldset>
								<%if(category!=null){ %>
								<input name="categoryid" type="hidden" value="<%=category.getCategoryid() %>" />
								<%} %>
								<div class="form-group">
									<label class="col-lg-3 control-label">Main Category</label>
									<div class="col-lg-5">
										<select name="maincategoryid" data-placeholder="Choose a Country..." class="form-control" tabindex="2">
										<option value="">&nbsp;</option>
										<%for(int i=0;i<list.size();i++){
										Maincategory dto=(Maincategory)list.get(i);	
										
										%>
										<%if(category!=null && category.getMaincategoryid()==dto.getMaincategoryid()){ %>
										<option selected  value="<%=dto.getMaincategoryid()%>"><%=dto.getMaincategoryname() %></option>
										<%}else{ %>
										<option  value="<%=dto.getMaincategoryid()%>"><%=dto.getMaincategoryname() %></option>
										<%} %>
										
										<%} %>
										
										</select>
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-lg-3 control-label">Category Name</label>
									<div class="col-lg-5">
										<%if(category!=null){ %>
										<input type="text" class="form-control" name="categoryname" value="<%=category.getCategoryname() %>" />
										<%}else{ %>
										<input type="text" class="form-control" name="categoryname"  />
										<%} %>
									</div>
								</div>

								<div class="form-group">
									<label class="col-lg-3 control-label">Category logo url</label>
									<div class="col-lg-5">
										<%if(category!=null){ %>
										<div class="input-group">
												<input type="text" name="categorylogourl"  id="txturl" class="form-control" readonly="readonly" value="<%=category.getCategorylogourl() %>">
												<span class="input-group-btn">
													<span class="btn btn-default btn-file">
														Browse<input type="file" name="userimageurl" id="categorylogourl" onchange="return ValidateFileUpload()">
													</span>
												</span>
										</div>
										<%}else{ %>
										<div class="input-group">
												<input type="text" class="form-control"  id="txturl" readonly="readonly">
												
												<span class="input-group-btn">
													<span class="btn btn-default btn-file">
														Browse<input type="file" name="userimageurl" id="categorylogourl" onchange="return ValidateFileUpload()">
													</span>
												</span>
										</div>
										<%} %>
									</div>
								</div>
							</fieldset>

							<div class="form-group">
								<div class="col-lg-9 col-lg-offset-3">
									<button type="submit" class="btn btn-success">Save</button>
								</div>
							</div>
						</form>
					</div><!-- /.the-box -->
							  </div><!-- /.panel-body -->
							  <div class="panel-footer"></div>
					</div>
					
				
					
				
					
					
					
				
				</div><!-- /.container-fluid -->
				
				
				
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
		
		
		<script type="text/javascript">
	var sone = 0;	
    function ValidateFileUpload() {
        var fuData = document.getElementById('categorylogourl');
        var FileUploadPath = fuData.value;
       // alert(FileUploadPath);
        var imageSize = fuData.files[0].size;
        var maxSize = 1024 * 1024 * 2 ;  // size 2 MB
        if(imageSize> maxSize){
        	alert("Sorry image big size please choose another.");
        	
        	//$("#imgerror").text("Sorry image sizen too big please choose another.");
        	//$("#imgerror").css("color", "red");
        }else{
      //  alert("This is file size of image you have to controller ....."+imageSize);
        //To check if user upload any file
        if (FileUploadPath == '') {
            alert("Please upload an image");
			
        } else {
            var Extension = FileUploadPath.substring(FileUploadPath.lastIndexOf('.') + 1).toLowerCase();
			//The file uploaded is an image
			if (Extension == "gif" || Extension == "png" || Extension == "gif" || Extension == "jpeg" || Extension == "jpg") {				    
//                 if (fuData.files && fuData.files[0]) {
//                     var reader = new FileReader();
//                       reader.onload = function(e) {
//                          $('#photoimg').attr('src', e.target.result);
//                          /* ChangeCover Button */
//                          if(sone == 0){
//                  	    $("#appendbtn").append("<input type='submit' value='Change Cover' class='btn btn-danger btn-sm'/>");
//                  	    	sone=1; //not appear button when change image many times
//                          }
//                     };
//                     reader.readAsDataURL(fuData.files[0]);
//                 }
                alert("yes");

            } 

	//The file upload is NOT an image
	else {
	                alert("Photo only allows file types of GIF, PNG, JPG, JPEG and BMP. ");
					$("#categorylogourl").val(null);
					$("#txturl").val("");
	            }
	        }
	    }
    }
	</script> 
	
	
	</body>
</html>