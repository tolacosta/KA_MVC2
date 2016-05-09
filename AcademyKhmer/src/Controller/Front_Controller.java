package Controller;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.Admin.ActionAdminDashboard;
import Controller.Admin.ActionCountUserVideo;
import Controller.AllCategoryAction.ActionListAllCategories;
import Controller.AllCategoryAction.ActionListVideosInCategory;
import Controller.AllCategoryAction.ActionViewCategory;
import Controller.CategoryAction.ActionAddCategory;
import Controller.CategoryAction.ActionCategoryVideo;
import Controller.CategoryAction.ActionDeleteCategory;
import Controller.CategoryAction.ActionGetCategory;
import Controller.CategoryAction.ActionListCategory;
import Controller.CategoryAction.ActionToAddCategory;
import Controller.CategoryAction.ActionUpdateCategory;
import Controller.Comment.ActionAddComment;
import Controller.Comment.ActionAddReplyComment;
import Controller.Comment.ActionListComments;
import Controller.Department.ActionAddDepartment;
import Controller.Department.ActionDeleteDepartment;
import Controller.Department.ActionListDepartment;
import Controller.Department.ActionUpdateDepartment;
import Controller.Forum.ActionAddAnswerQuestion;
import Controller.Forum.ActionAddPost;
import Controller.Forum.ActionAddQuestionPage;
import Controller.Forum.ActionGetFooter;
import Controller.Forum.ActionGetPost;
import Controller.Forum.ActionListPost;
import Controller.Forum.ActionListQuestion;
import Controller.Forum.ActionSelectAnswer;
import Controller.Forum.ActionVote;
import Controller.ForumCategory.ActionAddForumCategory;
import Controller.ForumCategory.ActionDeleteForumCategory;
import Controller.ForumCategory.ActionGetForumCategory;
import Controller.ForumCategory.ActionListForumCategory;
import Controller.ForumCategory.ActionUpdateForumCategory;
import Controller.ForumUser.AcionListForumUsers;
import Controller.HistoryAction.ActionDeleteAllHistory;
import Controller.HistoryAction.ActionDeleteHistory;
import Controller.HistoryAction.ActionListHistory;
import Controller.Home.ActionGetInformation;
import Controller.Log.ActionShowCategoryOfUser;
import Controller.Log.ActionShowListCategory;
import Controller.Log.ActionShowLogOfDepartmentInUniversity;
import Controller.Log.ActionShowLogOfUniversity;
import Controller.Log.ActionShowLogOfUserInDepartment;
import Controller.Log.ActionShowLogUserOfCategory;
import Controller.MainCategoryAction.ActionAddMainCategory;
import Controller.MainCategoryAction.ActionDeleteMainCategory;
import Controller.MainCategoryAction.ActionGetMainCategory;
import Controller.MainCategoryAction.ActionListMainCategory;
import Controller.MainCategoryAction.ActionUpdateMainCategory;
import Controller.PlaylistAction.ActionAddVideoToPlst;
import Controller.PlaylistAction.ActionCreatePlaylist;
import Controller.PlaylistAction.ActionDeletePlaylist;
import Controller.PlaylistAction.ActionDeleteVideoFromPlaylist;
import Controller.PlaylistAction.ActionGetPlaylist;
import Controller.PlaylistAction.ActionGetPlaylistForUpdate;
import Controller.PlaylistAction.ActionListPlaylist;
import Controller.PlaylistAction.ActionListPlaylistName;
import Controller.PlaylistAction.ActionUpdatePlaylist;
import Controller.PreCourse.ActionAddPreCourse;
import Controller.PreCourse.ActionDeletePreCourse;
import Controller.PreCourse.ActionEditPreCourse;
import Controller.PreCourse.ActionGetPreCourse;
import Controller.PreCourse.ActionListPreCourse;
import Controller.PreCourse.CheckPreCourse;
import Controller.PreCourse.GetPreCourseStudent;
import Controller.PreCourse.UpdatePreCourseStudent;
import Controller.Tutorial.ActionAddTutorial;
import Controller.Tutorial.ActionDeleteTutorial;
import Controller.Tutorial.ActionEditTutorial;
import Controller.Tutorial.ActionGetTutorial;
import Controller.Tutorial.ActionListTitleToturial;
import Controller.Tutorial.ActionListTutorial;
import Controller.Tutorial.ActionTutorial;
import Controller.Tutorial.ActionTutorialDetail;
import Controller.Tutorial.ActionTutorialDetailFirst;
import Controller.University.ActionAddUniversity;
import Controller.University.ActionDeleteUniversity;
import Controller.University.ActionListUniversity;
import Controller.University.ActionUpdateUniversity;
import Controller.UserAction.ActionAddUser;
import Controller.UserAction.ActionAddUserAdmin;
import Controller.UserAction.ActionAdminListAllUser;
import Controller.UserAction.ActionAdminUpdateUserType;
import Controller.UserAction.ActionChangeCoverPhoto;
import Controller.UserAction.ActionChangePassword;
import Controller.UserAction.ActionCheckEmail;
import Controller.UserAction.ActionCheckOldPassword;
import Controller.UserAction.ActionLogIn;
import Controller.UserAction.ActionLogout;
import Controller.UserAction.ActionRegisterUser;
import Controller.UserAction.ActionRessetPwd;
import Controller.UserAction.ActionSendMailToChPWD;
import Controller.UserAction.ActionSignupLogin;
import Controller.UserAction.ActionUpdateUser;
import Controller.UserAction.Register;
import Controller.VideoAction.ActionAddVideo;
import Controller.VideoAction.ActionDeleteUserVideoFront;
import Controller.VideoAction.ActionDeleteVideo;
import Controller.VideoAction.ActionFrontAddVideo;
import Controller.VideoAction.ActionFrontListUserVideos;
import Controller.VideoAction.ActionFrontUpdateVideo;
import Controller.VideoAction.ActionGetVideo;
import Controller.VideoAction.ActionListVideo;
import Controller.VideoAction.ActionListVideotToAddToPlaylist;
import Controller.VideoAction.ActionPlayVideo;
import Controller.VideoAction.ActionSearchVideo;
import Controller.VideoAction.ActionStopVideo;
import Controller.VideoAction.ActionUpdateVideo;
import Controller.VideoAction.ActionVoteVideo;
import Controller.shortcoure.ActionCheckUserRegisteredSC;
import Controller.shortcoure.ActionGetShortCourseStudent;
import Controller.shortcoure.ActionListAllApplicantsCoruses;
import Controller.shortcoure.ActionListApplicantCourses;
import Controller.shortcoure.ActionListUni;
import Controller.shortcoure.ActionRegisterSC;
import Controller.shortcoure.ActionShortCourseRegister;
import Controller.shortcoure.ActionShortCourseUpdate;
import Controller.shortcoure.DeleteRegisteredCourse;
import Controller.shortcoure.GetRegisteredCourse;


@WebServlet("*.act")
public class Front_Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Front_Controller() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());
		ActionForward forward = null;
		Action action = null;
		
		
		//SETTING DEFAULT COOKIE FOR KHMER LANGUAGE
		Cookie[] cookies = request.getCookies();
		boolean hasCookie=false;
	      if(cookies!=null){
		      for(int i=0; i<cookies.length; i++){
		    	  if(cookies[i].getName().equalsIgnoreCase("lang")){
		    		  hasCookie=true;
		    		  break;
		    	  }
		      }
		      if(!hasCookie){
		    	  response.addCookie(new Cookie("lang", "km"));
		      }
	      }else{	    	  
	    	  response.addCookie(new Cookie("lang", "km"));
	      }
		
		
		System.out.println(command);

		switch(command){
		//Ponreay
			case "/elearning/register.act":
				action = new Register();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			break;
			case "/elearning/login.act":
				action = new ActionLogIn();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			
			break;
			case "/elearning/search.act":
				action = new ActionSearchVideo();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			
			break;
			case "/elearning/category.act":
				action=new ActionCategoryVideo();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			
			break;
			case "/elearning/dashboard.act":
				action=new ActionDashboard();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			
			break;
				
		// Ponreay
		
		
		
		// End Ponreay
	
		// Vuthea
			case "/admin/countuservideo.act":
				action = new ActionCountUserVideo();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case "/forum/getquestion.act" :
				action = new ActionGetPost();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case "/forum/getfooter.act" :
				action = new ActionGetFooter();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case "/forum/question.act":
				forward= new ActionForward();
				forward.setPath("topic_1.jsp");
				forward.setRedirect(false);
				break;
			
			case "/elearning/registeruser.act" :
				action = new ActionAddUser();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case "/elearning/logout.act" :
				action = new ActionLogout();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			
			case "/elearning/actionregisteruser.act" :
				action = new ActionRegisterUser();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;	
			case "/elearning/index.act":
				action = new ActionGetInformation();
			try {
				forward = action.execute(request, response);
			} catch (Exception e3) {
				e3.printStackTrace();
			}
			forward = new ActionForward();
			forward.setPath("index_sample.jsp");
			forward.setRedirect(false);
				break;
			case "/elearning/allcourse.act":
				forward= new ActionForward();
				forward.setPath("_dashboard.jsp");
				forward.setRedirect(false);
				break;
			case "/elearning/account.act":
				action = new ActionGetInformation();
				try {
					forward = action.execute(request, response);
				} catch (Exception e7) {
					e7.printStackTrace();
				}
				forward= new ActionForward();
				forward.setPath("_adduser.jsp");
				forward.setRedirect(false);
				break;
			case "/elearning/loginaftersingup.act":
				action = new ActionSignupLogin();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case "/elearning/checkemail.act":
				action = new ActionCheckEmail();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case "/checkemail.act":
				action = new ActionCheckEmail();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case "/tutorials/checkemail.act":
				action = new ActionCheckEmail();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case "/elearning/checkoldpassword.act":
				action = new ActionCheckOldPassword();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;	
			case "/checkoldpassword.act":
				action = new ActionCheckOldPassword();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case "/tutorials/checkoldpassword.act":
				action = new ActionCheckOldPassword();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case "/elearning/updateusrinfo.act":
				action = new ActionUpdateUser();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;	
				
			case "/elearning/changeuserpassword.act":
				action = new ActionChangePassword();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;	
			case "/elearning/changeusercover.act":
				action = new ActionChangeCoverPhoto();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;	
				
				//Admin
			case "/admin/checkoldpassword.act":
				action = new ActionCheckOldPassword();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;	
			case "/admin/logout.act" :
				action = new ActionLogout();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			
			case "/admin/changeuserpassword.act":
				action = new ActionChangePassword();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case "/admin/index.act":
				action = new ActionAdminDashboard();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break; 
				
			
				
				//Forum
			case "/forum/getdashboard.act":
				action = new ActionListPost();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case "/forum/changeuserpassword.act":
				action = new ActionChangePassword();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case "/forum/logout.act" :
				action = new ActionLogout();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case "/forum/checkoldpassword.act":
				action = new ActionCheckOldPassword();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case "/forum/actionregisteruser.act" :
				action = new ActionRegisterUser();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case "/forum/loginaftersingup.act":
				action = new ActionSignupLogin();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case "/forum/checkemail.act":
				action = new ActionCheckEmail();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case "/forum/register.act":
				action = new Register();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			break;
			case "/forum/login.act":
				action = new ActionLogIn();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			
			break;
			case "/forum/askquestion.act":
				action = new ActionAddQuestionPage();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case "/forum/addpost_pro.act":
				action = new ActionAddPost();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case "/elearning/add_replycomment.act":
				action = new ActionAddReplyComment();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
				
			case "/forum/getlistusers.act":
				action = new AcionListForumUsers();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case "/forum/users.act":
				forward= new ActionForward();
				forward.setPath("index.act");
				forward.setRedirect(false);
				break;
				
				
			case "/forum/addanswerquestion.act":
				action = new ActionAddAnswerQuestion();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
				
			case "/forum/votequestion.act":
				action = new ActionVote();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
				
				
			//Tutorials
			case "/tutorials/index.act":
				action = new ActionGetInformation();
				try {
					forward = action.execute(request, response);
				} catch (Exception e3) {
					e3.printStackTrace();
				}
				/*forward= new ActionForward();
				forward.setPath("detail.act?cid=31");
				forward.setRedirect(false);*/
				break;
			case "/tutorials/dashboard.act":
				forward= new ActionForward();
				forward.setPath("detail.act?cid=31");
				forward.setRedirect(false);
				break;
			case "/admin/addtutorial.act":
				action = new ActionTutorial();
				try{
					forward = action.execute(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
				break;
			case "/admin/addtutorial_pro.act":
				action = new ActionAddTutorial();
				try{
					forward = action.execute(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
				break;
			case "/admin/listtutorial.act" :
				action = new ActionListTutorial();
				try{
					forward = action.execute(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
				
				break;
			case "/admin/updatetutorial_pro.act":
				action = new ActionEditTutorial();
				try{
					forward = action.execute(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
				
				break;
			case "/admin/gettutorialpostupdate.act":
				action = new ActionGetTutorial();
				try{
					forward = action.execute(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
				
				break;	
			case "/admin/deletetutorial_pro.act":
				action = new ActionDeleteTutorial();
				try{
					forward = action.execute(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
				
				break;
			case "/tutorials/detail_pro.act":
				action = new ActionTutorialDetail();
				try{
					forward = action.execute(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
				break;
			case "/tutorials/detail_first.act":
				action = new ActionTutorialDetailFirst();
				try{
					forward = action.execute(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
				break;	
			case "/tutorials/detail.act":
				forward= new ActionForward();
				forward.setPath("new_single_post.jsp");
				forward.setRedirect(false);
				break;
			case "/tutorials/listtitletutorial.act":
				action = new ActionListTitleToturial();
				try{
					forward = action.execute(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
				break;
			case "/admin/listcomment.act":
				action = new ActionListComments();
				try{
					forward = action.execute(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
				break;
				
		// End Vuthea
	
			
		// Tola
			/* admin */	
			case "/admin/createplaylist.act":
				action = new ActionCreatePlaylist();
				try{
					forward = action.execute(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
				break;
		
			case "/admin/playlist.act":
				action = new ActionListPlaylist();
				try{
					forward = action.execute(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
				break;
				
			case "/admin/deleteplaylist.act":
				action = new ActionDeletePlaylist();
				try{
					forward = action.execute(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
				break;
			case "/admin/listvideoinplaylist.act":
				action = new ActionGetPlaylist();
				try{
					forward = action.execute(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
				break;
			case "/admin/updateplaylist.act":
				action = new ActionUpdatePlaylist();
				try{
					forward = action.execute(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
				break;
			case "/admin/listplaylistname.act":
				action = new ActionListPlaylistName();
				try{
					forward = action.execute(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
				break;
				
			case "/admin/adminupdateusertype.act":
				action = new ActionAdminUpdateUserType();
				try{
					forward = action.execute(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
				break;
				
			case "/admin/adminlistalluser.act":
				action = new ActionAdminListAllUser();
				try{
					forward = action.execute(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
				break;
				
			case "/admin/getplaylist.act":
				action = new ActionGetPlaylist();
				try{
					forward = action.execute(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
				break;
				
			case "/admin/getplaylistforupdate.act":
				action = new ActionGetPlaylistForUpdate();
				try{
					forward = action.execute(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
				break;
			case "/admin/adduseradmin.act":
				action = new ActionAddUserAdmin();
			try {
				forward = action.execute(request, response);
			} catch (Exception e6) {
				e6.printStackTrace();
			}
				break;
			case "/admin/user.act":
				forward= new ActionForward();
				forward.setPath("user.jsp");
				forward.setRedirect(false);
				break;
				
			case "/admin/adduser.act":
				action = new ActionGetInformation();
				try {
					forward = action.execute(request, response);
				} catch (Exception e5) {
					e5.printStackTrace();
				}
				forward= new ActionForward();
				forward.setPath("adduser.jsp");
				forward.setRedirect(false);
				break;
				
			/* elearning */	
			case "/elearning/playlistdetail.act":
				forward= new ActionForward();
				forward.setPath("playlistdetail.jsp");
				forward.setRedirect(false);
				break;
				
			case "/elearning/listuservideos.act":
				action = new ActionFrontListUserVideos();
				try{
					forward = action.execute(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
				break;
				
			case "/elearning/deleteuservideofront.act":
				action = new ActionDeleteUserVideoFront();
				try{
					forward = action.execute(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
				break;
				
			case "/elearning/listvideoinplaylist.act":
				action = new ActionGetPlaylist();
				try{
					forward = action.execute(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
				break;
				
			case "/elearning/getplaylistforupdate.act":
				action = new ActionGetPlaylistForUpdate();
				try{
					forward = action.execute(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
				break;
				
			case "/elearning/historylist.act":
				action = new ActionListHistory();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
				
			case "/elearning/deletehistory.act":
				action = new ActionDeleteHistory();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case "/elearning/deleteallhistory.act":
				action = new ActionDeleteAllHistory();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case "/elearning/playlist.act":
				action = new ActionListPlaylist();
				try{
					forward = action.execute(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
				break;
			case "/elearning/createplaylist.act":
				action = new ActionCreatePlaylist();
				try{
					forward = action.execute(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
				break;
			case "/elearning/deleteplaylist.act":
				action = new ActionDeletePlaylist();
				try{
					forward = action.execute(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
				break;
			case "/elearning/getplaylist.act":
				action = new ActionGetPlaylist();
				try{
					forward = action.execute(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
				break;
			case "/elearning/updateplaylist.act":
				action = new ActionUpdatePlaylist();
				try{
					forward = action.execute(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
				break;
			case "/elearning/mypublicprofile.act":
				forward = new ActionForward();
				forward.setPath("mypublicprofile.jsp");
				forward.setRedirect(false);
				break;
			case "/elearning/playlistname.act":
				action = new ActionListPlaylistName();
				try{
					forward = action.execute(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
				break;
			case "/elearning/addvideotoplaylist.act":
				action = new ActionAddVideoToPlst();
				try{
					forward = action.execute(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
				break;
				
			case "/elearning/deletevideofromplaylist.act":
				action = new ActionDeleteVideoFromPlaylist();
				try{
					forward = action.execute(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
				break;
				
			case "/elearning/video.act":
				action = new ActionGetVideo();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				};
				break;
			case "/elearning/addvideo_pro.act":
				action = new ActionFrontAddVideo();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
				
			case "/elearning/updatevideo_pro.act":
				action = new ActionFrontUpdateVideo();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
				
			case "/elearning/listvideotoaddtoplaylist.act":
				action = new ActionListVideotToAddToPlaylist();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
				
			case "/elearning/sendmailtochpwd.act":
				action = new ActionSendMailToChPWD();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
				
			case "/elearning/ressetpwd.act":
				action = new ActionRessetPwd();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			
				
				//forum 
			case "/forum/selectanswer.act":
				action = new ActionSelectAnswer();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;	
				
				
			case "/forum/listquestion.act":
				action = new ActionListQuestion();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
				
			case "/forum/list.act":
				forward = new ActionForward();
				forward.setPath("list_question.jsp");
				forward.setRedirect(false);
				break;	
				
				
			// Main page
			case "/login.act":
				action = new ActionLogIn();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			
			break;
			
			case "/logout.act" :
				action = new ActionLogout();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
				
			case "/actionregisteruser.act" :
				action = new ActionRegisterUser();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;	
			
			case "/loginaftersingup.act":
				action = new ActionSignupLogin();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
				
			case "/index.act":
					action = new ActionGetInformation();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				//forward = new ActionForward();
				//forward.setPath("index.jsp");
				//forward.setRedirect(false);
				break;
				
			case "/sendmailtochpwd.act":
				action = new ActionSendMailToChPWD();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
				
			case "/ressetpwd.act":
				action = new ActionRessetPwd();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
				
			case "/changeuserpassword.act":
				action = new ActionChangePassword();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;	
				
			
		// End Tola
				
	
		// Muny
			case "/admin/maincategory.act":
				forward = new ActionForward();
				forward.setPath("maincategory.jsp");
				forward.setRedirect(false);
				break;
			case "/admin/listmaincategorypro.act":
				action = new ActionListMainCategory();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
				///
				///
			case "/admin/addmaincategory.act":
				forward = new ActionForward();
				forward.setPath("addmaincategory.jsp");
				forward.setRedirect(false);
				break;
			case "/admin/addmaincategorypro.act":
				action = new ActionAddMainCategory();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
				//
				//
			case "/admin/deletemaincategorypro.act":
				action = new ActionDeleteMainCategory();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case "/admin/updatemaincategory.act":
				action = new ActionGetMainCategory();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case "/admin/updatemaincategorypro.act":
				action = new ActionUpdateMainCategory();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
		// End Muny
	
		// Sonita
		
			case "/admin/historylist.act":
				action = new ActionListHistory();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case "/admin/deletehistory.act":
				action = new ActionDeleteHistory();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case "/admin/deleteallhistory.act":
				action = new ActionDeleteAllHistory();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case "/admin/history.act":
				forward= new ActionForward();
				forward.setPath("history.jsp");
				forward.setRedirect(false);
				break;
				
		// End Sonita
		
		// Menghok
				
			case "/forum/index.act":
				action = new ActionGetInformation();
				try {
					forward = action.execute(request, response);
				} catch (Exception e3) {
					e3.printStackTrace();
				}
				forward= new ActionForward();
				forward.setPath("dashboard.jsp");
				forward.setRedirect(false);
				break;
			case "/admin/video.act":
				action = new ActionGetVideo();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				};
				break;
		
			case "/admin/addvideo_pro.act":
				action = new ActionAddVideo();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
				
			case "/admin/updatevideo_pro.act":
				action = new ActionUpdateVideo();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
		
			case "/admin/videolist.act":
				action = new ActionListVideo();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
				
			case "/elearning/play.act":
				action = new ActionPlayVideo();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
				
			case "/admin/deletevideo.act":
				action = new ActionDeleteVideo();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
				
			case "/elearning/vote.act":
				action = new ActionVoteVideo();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
				
			case "/elearning/add_comment.act":
				action = new ActionAddComment();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
				
				
			case "/elearning/stopwatching.act":
				action = new ActionStopVideo();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
				
				
			case "/admin/log_listuniversity.act":
				action = new ActionShowLogOfUniversity();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;	
				
			case "/admin/log_listdepartment.act":
				action = new ActionShowLogOfDepartmentInUniversity();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
				
			case "/admin/log_listuserbydept.act":
				action = new ActionShowLogOfUserInDepartment();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
				
			case "/admin/log_listcatebyuser.act":
				action = new ActionShowCategoryOfUser();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
				
			case "/admin/log_listcategory.act":
				action = new ActionShowListCategory();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;

			case "/admin/log_listuserbycate.act":
				action = new ActionShowLogUserOfCategory();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
		// End Menghok
	
		// Sorady
			case "/elearning/view_category.act":
				action = new ActionViewCategory();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case "/elearning/list_all_categories.act":
				action = new ActionListAllCategories();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case "/elearning/list_videos_In_categories.act":
				action = new ActionListVideosInCategory();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
		// End Sorady
		
		// Piseth
			case "/admin/toaddcategory.act":
				action=new ActionToAddCategory();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
				break;
			case "/admin/addcategory.act":
				action=new ActionAddCategory();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
				break;
			
			case "/admin/category.act":
				forward = new ActionForward();
				forward.setPath("category.jsp");
				forward.setRedirect(false);
				break;
			case "/admin/listcategorypro.act":
				action = new ActionListCategory();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case "/admin/deletecategorypro.act":
				action = new ActionDeleteCategory();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case "/admin/updatecategory.act":
				action = new ActionGetCategory();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case "/admin/updatecategorypro.act":
				action = new ActionUpdateCategory();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			/*forum category*/
			case "/admin/addforumcategory.act":
				forward = new ActionForward();
				forward.setPath("addforumcategory.jsp");
				forward.setRedirect(false);
				break;
			case "/admin/addforumcategorypro.act":
				action = new ActionAddForumCategory();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case "/admin/forumcategory.act":
				forward = new ActionForward();
				forward.setPath("forumcategory.jsp");
				forward.setRedirect(false);
				break;
			case "/admin/forumcategorypro.act":
				action = new ActionListForumCategory();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case "/admin/deleteforumcategorypro.act":
				action = new ActionDeleteForumCategory();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case "/admin/updateforumcategory.act":
				action = new ActionGetForumCategory();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case "/admin/updateforumcategorypro.act":
				action = new ActionUpdateForumCategory();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
				
			
		// End Piseth
				
			/*
			 * Bunrong Leang 
			 * =============
			 * Department implementation
			 */
			case "/admin/adddepartment.act":
				forward = new ActionForward();
				forward.setPath("addDepartment.jsp");
				forward.setRedirect(false);
				break;
			case "/admin/adddepartmentaction.act":
				action = new ActionAddDepartment();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
				break;
			case "/admin/updatedepartment.act":
				action = new ActionUpdateDepartment();
			try {
				forward = action.execute(request, response);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
				break;
			case "/admin/deletedepartment.act":
				action = new ActionDeleteDepartment();
			try {
				forward = action.execute(request, response);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
				break;
			case "/admin/listdepartment.act":
				action = new ActionListDepartment();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
				break;
				
			/*
			 * end of Department implementation
			 */
				
			/*
			 * Bunrong Leang
			 * =============
			 * University implementation
			 */
			case "/admin/adduniversityaction.act":
				forward = new ActionForward();
				forward.setPath("adduniversity.jsp");
				forward.setRedirect(false);
				break;
			case "/admin/adduniversity.act":
				action = new ActionAddUniversity();
				try{
					forward = action.execute(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
				break;
//			case "/admin/adduniversity_pro.act":
//				action = new ActionAddUniversity();
//				try{
//					forward = action.execute(request, response);
//				}catch(Exception e){
//					e.printStackTrace();
//				}
//				break;
			case "/admin/deleteuniversity.act":
				action = new ActionDeleteUniversity();
				try{
					forward = action.execute(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
				break;
			case "/admin/deleteuniversity_pro.act":
				action = new ActionDeleteUniversity();
				try{
					forward = action.execute(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
				
				break;
			case "/admin/updateuniversity.act":
				action = new ActionUpdateUniversity();
				try{
					forward = action.execute(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
				
				break;
			case "/admin/listuniversity.act":
				action = new ActionListUniversity();
				try{
					forward = action.execute(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
				
				break;
				
			/*
			 * end of University implementation
			 */
				
			/*
			 * LEANG BUNRONG BEGIN PRE-COURSE
			 */
			case "/admin/listprecourse.act":
				action = new ActionListPreCourse();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
				break;
			case "/admin/getprecourse.act":
				action = new ActionGetPreCourse();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
				break;
			case "/admin/editprecourse.act":
				action = new ActionEditPreCourse();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
				break;
			case "/admin/deleteprecourse.act":
				action = new ActionDeletePreCourse();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
				break;
			case "/elearning/addprecourse.act":
				action = new ActionAddPreCourse();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
			case "/elearning/precourse.act":
				forward = new ActionForward();
				forward.setPath("precourse.jsp");
				forward.setRedirect(false);
				break;
			case "/elearning/checkprecourses.act":
				action = new CheckPreCourse();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			break;
			case "/elearning/getprecoursestudent.act":
				action = new GetPreCourseStudent();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			break;
			case "/elearning/updateprecourse.act":
				action = new UpdatePreCourseStudent();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			break;
					
			/*
			 * END PRE-COURSE
			 */
				
			// Short Course
						case "/elearning/shortcourse.act":
							forward = new ActionForward();
							forward.setPath("shortcourse.jsp");
							forward.setRedirect(false);
							break;
							
							
						case "/elearning/shortcourse_register.act":
							action = new ActionShortCourseRegister();
							try {
								forward = action.execute(request, response);
							} catch (Exception e) {
								e.printStackTrace();
							}
						break;
						
						case "/elearning/shortcourse_update.act":
							action = new ActionShortCourseUpdate();
							try {
								forward = action.execute(request, response);
							} catch (Exception e) {
								e.printStackTrace();
							}
						break;
						
						case "/elearning/get_shortcourse_student.act":
							action = new ActionGetShortCourseStudent();
							try {
								forward = action.execute(request, response);
							} catch (Exception e) {
								e.printStackTrace();
							}
						break;
							
						case "/elearning/list_applicant_courses.act":
							action = new ActionListApplicantCourses();
							try {
								forward = action.execute(request, response);
							} catch (Exception e) {
								e.printStackTrace();
							}
						break;	
						
						case "/elearning/is_registered_sc.act":
							action = new ActionCheckUserRegisteredSC();
							try {
								forward = action.execute(request, response);
							} catch (Exception e) {
								e.printStackTrace();
							}
						break;
						
						case "/elearning/get_registered_course.act":
							action = new GetRegisteredCourse();
							try {
								forward = action.execute(request, response);
							} catch (Exception e) {
								e.printStackTrace();
							}
						break;
						
						case "/elearning/register_sc.act":
							action = new ActionRegisterSC();
							try {
								forward = action.execute(request, response);
							} catch (Exception e) {
								e.printStackTrace();
							}
						break;
						
						case "/elearning/delete_registerd_c.act":
							action = new DeleteRegisteredCourse();
							try {
								forward = action.execute(request, response);
							} catch (Exception e) {
								e.printStackTrace();
							}
						break;
						
						case "/elearning/list_uni.act":
							action = new ActionListUni();
							try {
								forward = action.execute(request, response);
							} catch (Exception e) {
								e.printStackTrace();
							}
						break;
						
						case "/elearning/list.act":
							action = new ActionListAllApplicantsCoruses();
							try {
								forward = action.execute(request, response);
							} catch (Exception e) {
								e.printStackTrace();
							}
						break;
						
						
						
			default :
				forward = new ActionForward();
				forward.setPath("404.jsp");
				forward.setRedirect(false);
				break;
		}
		
		if (forward != null) {
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}

		}

	}
}