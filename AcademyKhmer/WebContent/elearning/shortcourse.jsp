<%@page import="model.dto.User"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
 <%@page import="Controller.Common_method"%>
<%@page import="model.dto.University"%>
<%@page import="model.dto.Department"%>
<%@page import="java.util.ArrayList"%>
<%
		User usr= new User();
		if( session.getAttribute("ka_user") != null){
			
			 usr = (User) session.getAttribute("ka_user");
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
		<title>Short Course | Khmer Acedemy</title>
 
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
						
						
							<%if( session.getAttribute("ka_user") != null){ %>
							<span><a style="margin-bottom:10px" href="list_applicant_courses.act" class="btn btn-info btn-perspective" >My Registered Course</a><br/></span>
							<%} %>
							
							<div class="panel panel-primary">
								  <div class="panel-heading">
											
										Short Course Register Information:
										
								  </div>
								  
								  
								  <div class="panel-body" style="    min-height: 700px;">
								  
								  	
								  
								  	<%if( session.getAttribute("ka_user") != null){ %>
										
										
										<!-- Form Khmer -->																						
										<form class="form-horizontal" action="#" method="post" id="frmaddshortcourse">	
										
										<input type="hidden" name="id" id="id" >
										
										<input type="hidden" name="s_id" id="s_id" >
										
										<input type="hidden" name="hide_c_id" id="hide_c_id" >
										
										<input type="hidden" name="old_shift" id="old_shift" >
										
										<div id="isPaid" style="display: none" class="alert alert-info alert-block fade in alert-dismissable">
										  <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
										    Please come to Korea Software HRD Center for paying tuition fee for this course!
										  <a href="http://kshrd.com.kh/jsp/contactus.hrd" target="_blank" class="alert-link"><Strong>Korea Software HRD Center</Strong></a>
										</div>
										<% if(request.getParameter("m") != null && request.getParameter("t") == null){ %>
										<div  class="alert alert-danger alert-block fade in alert-dismissable">
										  <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
										   You cannot register this course again! Please check your registered list <a href="list_applicant_courses.act" style="color:#F3CE10"><b>registered list.</b></a>
										</div>
										<% } %>
										
										<% if(request.getParameter("t") != null){ %>
										<div  class="alert alert-danger alert-block fade in alert-dismissable">
										  <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
										   Please choose another time! You already registered a course with this time. Please check your <a href="list_applicant_courses.act" style="color:#F3CE10"><b>registered list.</b></a>
										</div>
										<% } %>
										
										<div class="col-sm-6">
										
										<div class="form-group">
												<label class="col-lg-3 control-label">Course: </label>
												<div class="col-lg-9">
													<select name="course" class="form-control" id="course">
														<option value="1">iOS Application Development</option>													
														<option value="2">Android App Development</option>
														<option value="3">Spring Framework</option>
														<option value="4">Java Enterprise Edition(J2EE)</option>
														<option value="5">Java Standart Edition(J2SE)</option>
														<option value="6">Oracle DBMS</option>
														<option value="7">Web Design</option>
													</select>
											
											</div>
										</div>
										
										<div class="form-group">
												<label class="col-lg-3 control-label">Shift: </label>
												<div class="col-lg-9">
													
													<input name="shift" type="radio" value="1" required="required"> 02:00 - 04:00 AM (Mon-Fri) <br/>
													<input name="shift" type="radio" value="2" required="required"> 06:00 - 08:00 PM (Mon-Fri) <br/>
													<input name="shift" type="radio" value="3" required="required"> 08:00 - 10:00 PM (Sat-Sun)
											
												</div>
										</div>
										
								<span id="student_info" style="display: none">
										
										<div class="form-group">
											<label class="col-lg-3 control-label">Full name: <span class="required">*</span></label>
											<div class="col-lg-9">
												<input type="text" required class="form-control" id="fullname" name="fullname" value=""/>
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-3 control-label">Tel: <span class="required">*</span></label>
											<div class="col-lg-9">
												<input type="text" required class="form-control" id="phonenumber" name="phonenumber" value="<%if(usr.getPhonenumber()!=null){out.print(usr.getPhonenumber());}else{out.print("");} %>" />
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-3 control-label">Email: <span class="required">*</span></label>
											<div class="col-lg-9">
												<input type="email" required class="form-control" id="email" name="email" value="<%if(usr.getEmail()!=null){out.print(usr.getEmail());}else{out.print("");}%>"/>
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-3 control-label">Date Of Birth: <span class="required">*</span></label>
											<div class="col-lg-9">
												<input type="text" required value="<% if(usr.getDateofbirth()!=null){out.print( new SimpleDateFormat("yyyy-MM-dd").format(usr.getDateofbirth()) );}else{out.print("");}%>" name="dob" data-date-format="yyyy-mm-dd" class="form-control datepicker" id="calendar" >
											</div>
										</div>
										<div class="form-group">
											<label class=" col-lg-3 control-label">University: <span class="required">*</span></label>
											<div class="col-lg-9">
<!-- 												<input type="text" class="form-control" id="university" name="university" placeholder="eg.RUPP" required/> -->
													<select class="form-control" id="university" name="university">
													
													</select>
											</div>

										</div>
										<div class="form-group">
												<label class="col-lg-3 control-label">Year: </label>
												<div class="col-lg-9">
													<select name="year" class="form-control" id="year" required="required">
														<option value="1">Year 1</option>													
														<option value="2">Year 2</option>
														<option value="3">Year 3</option>
														<option value="4">Year 4</option>
														<option value="G">Graduate</option>
													</select>
											
											</div>
										</div>
										<div class="form-group">
												<label class="col-lg-3 control-label">Gender: <span class="required">*</span></label>
												<div class="col-lg-5">
													<div class="radio">
														<label>
															<input class="gender" type="radio" name="gender" value="male" required data-bv-notempty-message="The gender is required" data-bv-field="gender" <%if(usr.getGender()!=null) if(usr.getGender().toLowerCase().contains("male")) {out.print("checked");} %>> Male
														</label>
													</div>
													<div class="radio">
														<label>
															<input class="gender" type="radio" name="gender" required value="female" <%if(usr.getGender()!=null) if(usr.getGender().toLowerCase().contains("female")) {out.print("checked");} %>> Female
														</label>
													</div>
											
											</div>
										</div>							
										
										<div class="form-group">
											<label class="col-lg-3 control-label">Address: <span class="required">*</span></label>
											<div class="col-lg-9">
												<textarea name="address" class="form-control" id="address" required="required"></textarea>
											</div>
										</div>
										
										
										<div class="form-group">
											<label class="col-lg-3 control-label">Comment: </label>
											<div class="col-lg-9">
												<textarea name="comment" class="form-control" id="comment"></textarea>
											</div>
										</div>
									
								</span>
										<!-- Button Post -->
										<div class="form-group" id="btnaction">
											<div class="col-lg-9 col-lg-offset-3">
												<button type="reset" class="btn btn-default btn-perspective">Cancel</button>
												<button id="msubmit" class="btn btn-info btn-perspective" type="submit">Register</button>
											</div>
										</div>
										<!-- Button Post -->
										</div>	
										<!-- /From -->
										</form>	
										
										<div class="col-sm-6">
											<img class="img-responsive" src="assets/img/shortcourse.png"/>
										</div>
						
										<%}else{ %>
										
										
										<div class="col-sm-12">
											<div>
												<img class="img-responsive" src="assets/img/shortcourse_banner.png"/>
											</div>
											
											
											
											
											
											<div>
												<div class="col-sm-6 inline-popups">
													<a data-toggle="dropdown" data-effect="mfp-zoom-in" class="dropdown-toggle btn btn-info btn-perspective pull-right btn-lg"  href="#form-login" >
														<strong><%= Common_method.getBundleValue(request, "lang", "login") %></strong>
													</a>
													
												</div>
							
												<div class="col-sm-6 inline-popups" >
												<span style="margin-right: 20px;">OR</span>
													<a data-toggle="dropdown" data-effect="mfp-zoom-in" class="dropdown-toggle btn btn-danger btn-perspective btn-lg"  href="#form-register" >
														<strong><%= Common_method.getBundleValue(request, "lang", "signup") %></strong>
													</a>
												</div>
											</div>										
										</div>
										
										
										<%} %>	
										
										<div class="col-sm-12">
												<div>
													<div class="col-sm-6">
													<h4>ព័ត៌មានលម្អិតបន្ថែមសូមចូលទៅកាន់តំណភ្ជាប់៖</h4>
													<h4><a href="https://goo.gl/51Mc3Q" target="_blank">- ខិត្តប័ណ្ណៈ https://goo.gl/51Mc3Q</a></h4>
													<h4><a href="https://goo.gl/is4JiS" target="_blank">- កម្មវិធីសិក្សាៈ https://goo.gl/is4JiS</a></h4>
													
													<h4><a href="https://www.youtube.com/playlist?list=PL_Uo-PlWEITF4J_3cjH18FyrcFjcnD6qB" target="_blank">-3rd Generation - Advanced Course Project Demo</a></h4>

													
													<h4>ទូរស័ព្ទទំនាក់ទំនងៈ ០២៣ ២៩ ១៣ ១៤/ ០៨៦ ២៩ ២៩ ១៤/ ០៩៩ ៧៦ ៨៤ ៨១/ ០១៧ ២២០ ៧០៧</h4>
													
													
													</div>
													
													<div class="col-sm-6" style="margin-top:5px">
														
														<a href="#" data-toggle="modal" data-target="#front-leaf">
															<img width="43%" style="float:left;height:150px;margin-right:5px" class="img-responsive" src="assets/img/front-leaflet.png"> 
														</a>
														
														<a href="#" data-toggle="modal" data-target="#back-leaf">
															<img width="43%" style="float:left;height:150px;" class="img-responsive" src="assets/img/back-leaflet.png"> 
														</a>
														
													</div>
												</div>
											</div>				
																	
									
									</div>
								  <!-- /.panel-body -->
							  <div class="panel-footer">
							  </div>
							</div>
							</div><!-- /.panel .panel-info -->
						</div>
				</div><!-- /.container-fluid -->
				
				<!-- Modal -->
				<div id="front-leaf" class="modal fade" role="dialog">
				  <div class="modal-dialog modal-lg" style="width: 63%;">
				
				    <!-- Modal content-->
				    <div class="modal-content">
				      <div class="modal-header">
				        <button type="button" class="close" data-dismiss="modal">&times;</button>
				        <h4 class="modal-title">Front Leaftlet </h4>
				      </div>
				      <div class="modal-body">
				        <img class="img-responsive" src="assets/img/front-leaflet.png"/>
				      </div>
				    
				    </div>
				
				  </div>
				</div>
				
				<div id="back-leaf" class="modal fade" role="dialog">
				  <div class="modal-dialog modal-lg" style="width: 63%;">
				
				    <!-- Modal content-->
				    <div class="modal-content">
				      <div class="modal-header">
				        <button type="button" class="close" data-dismiss="modal">&times;</button>
				        <h4 class="modal-title">Back Leaftlet </h4>
				      </div>
				      <div class="modal-body">
				        <img class="img-responsive" src="assets/img/back-leaflet.png"/>
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
		
		<%if( session.getAttribute("ka_user") != null){ %>
			
			<%if( request.getParameter("id") == null){ %>
			<script>
				$.post("is_registered_sc.act",{
					uid : <%= usr.getUserid()%>
				},function(data){
						console.log(data.isRegisterd);
						if(data.isRegisterd == true){
							
							<% if(request.getParameter("s_id") == null){ %>
								window.location.href = "list_applicant_courses.act";
							<% } %>
							
							$("#student_info").hide();
							
							$("#frmaddshortcourse").attr("action","register_sc.act");
							
							$("#s_id").val(<%= request.getParameter("s_id") %>);
							
							$("#fullname").attr("disabled", true);
							$("#phonenumber").attr("disabled", true);
							$("#calendar").attr("disabled", true);
							$("#university").attr("disabled", true);
							$("#address").attr("disabled", true);
							$("input[name=gender]").attr("disabled", true);
							
						}else{
							$("#frmaddshortcourse").attr("action","shortcourse_register.act");
							$("#fullname").attr("disabled", false);
							$("#phonenumber").attr("disabled", false);
							$("#calendar").attr("disabled", false);
							$("#university").attr("disabled", false);
							$("#address").attr("disabled", false);
							$("input[name=gender]").attr("disabled", false);
							
							$.post("list_uni.act",function(data){
								if(data.uni != null){
									$.each(data.uni, function(key, value){
										$('#university').append('<option value="'+value.universityId+'">'+value.universityName+'</option>');
									});
								}
							});
							
							
							
							$("#student_info").show();
						}
				});
			
				function disableform(){
					$("#username").attr("disabled", true);
					$("#email").attr("disabled", true);
					$("#phonenumber").attr("disabled", true);
					$("#calendar").attr("disabled", true);
					$("#address").attr("disabled", true);
					$("#university").attr("disabled", true);
					$("#comment").attr("disabled", true);
					$("#java").attr("disabled", true);
					$("#web").attr("disabled", true);
					$("#year").attr("disabled", true);
					$("input[name=gender]").attr("disabled", true);
					$("#btnaction").remove();
					$("#frmaddshortcourse").removeAttr('action');
				}
			
			</script>
			<%}else{ %>
				<script>
				
				$.post("get_registered_course.act",{
					courseId : <%= request.getParameter("id") %>
				},function(data){
					console.log(data);
					
						$("#course").val(data.registered_course.course);
					
						$("#hide_c_id").val(data.registered_course.course);
						
						$("#old_shift").val(data.registered_course.shift);
						
						
						$("#id").val(data.registered_course.id);

						$('input[name="shift"][value="' + data.registered_course.shift + '"]').prop('checked', true);
						
						$("#msubmit").text("Update");
						
						$("#frmaddshortcourse").attr("action","shortcourse_update.act");
						
						if(data.registered_course.isPaid == false){
							$("#isPaid").show();
						}else{
							$("#isPaid").hide();
						}
						
						$("#course").attr("disabled", true);
						
						$("#fullname").attr("disabled", true);
						$("#phonenumber").attr("disabled", true);
						$("#calendar").attr("disabled", true);
						$("#university").attr("disabled", true);
						$("#address").attr("disabled", true);
						$("input[name=gender]").attr("disabled", true);
						

				});
				
				</script>
			<% } %>
		<%} %>
		
		
		
		
	</body>
</html>