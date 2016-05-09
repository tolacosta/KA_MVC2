package Controller;


import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import java.io.*;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class Common_method {
	 public static final Charset ISO_8859_1 = Charset.forName("ISO-8859-1");
	    public static final Charset UTF_8 = Charset.forName("UTF-8");
	/*
	 * BundleValue must be store in 'app.properties' file in "src"
	 * for other Locale please add suffix ex. 'app_fr.properties' for Khmer language and 'app_km.properties' for Khmer
	 */
	
	public static String getBundleValue(HttpServletRequest request, String cookieKeyName, String dataKeyName) throws UnsupportedEncodingException{
	      Locale locale = null;
	      String cookieValue = null;
	      Cookie[] cookies = request.getCookies();
	      if(cookies!=null){
		      for(int i=0; i<cookies.length; i++){
		    	  if(cookies[i].getName().equalsIgnoreCase(cookieKeyName)){
		    		  cookieValue=cookies[i].getValue();
		    		  break;
		    	  }
		      }
	      }
	      
	      if (cookieValue != null) {
	        locale = new Locale(cookieValue);
	      } else {
	        locale = request.getLocale();
	      }
	      ResourceBundle RB = ResourceBundle.getBundle("app", locale);
	      /*byte ptext[] = RB.getString(dataKeyName).getBytes("ISO-8859-1"); 
	      String value = new String(ptext, "UTF-8");
	      return value;
	      */return RB.getString(dataKeyName);
	   }
	
	public static String readFromFile(HttpServletRequest request, String fileName) throws IOException{
		File file = new File(request.getServletContext().getRealPath("/") + fileName);
		//System.out.print("IT IS AT " + file.getAbsolutePath());
		InputStream in = new FileInputStream(file);
	    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
	    StringBuilder strBuilder = new StringBuilder();
	    String line;
	    while ((line = reader.readLine()) != null) {
	    	strBuilder.append(line +"\n");
	    }
	    reader.close();
	   // System.out.println(strBuilder.toString());
	    return strBuilder.toString();
	}
	
	public static boolean saveToFile(HttpServletRequest request, String fileName, String textBody){
        String file = request.getServletContext().getRealPath("/") + fileName;

        FileWriter filewriter;
		try {
			filewriter = new FileWriter(file, false);
			filewriter.write(textBody);
	        filewriter.close();
	        return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	
}
