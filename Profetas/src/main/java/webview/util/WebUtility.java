package webview.util;

import java.io.IOException;
import java.text.Normalizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;

public final class WebUtility {

	public static final String sessionMail = "userMail";
	public static final String sessionUserName = "userName";
	public static final String sessionUserPermissions = "userPermissions";
	public static final String SessionAccessToken = "userAccessToken";
	public static final String SessionUserLoginStatus = "userLoginSuccessfull";
	public static final String[] meses = {
		"Janeiro", "Fevereiro", "Março",
		"Abril", "Maio", "Junho", 
		"Julho", "Agosto", "Setembro", 
		"Outubro", "Novembro", "Dezembro"
	}; 

	/**
	 * Imprime na página o nome do usuário.
	 * @param session
	 * @param out
	 * @throws IOException
	 */
	public static void printName(HttpSession session, JspWriter out) throws IOException {
		String name = (String) session.getAttribute(sessionUserName);
		if(name != null) out.write("<label>" + name + "!</label>");
	}

	/**
	 * 
	 * @param request
	 * @param parameter
	 * @return
	 * @throws IOException
	 */
	public static String printLabel(HttpServletRequest request, String parameter) throws IOException {
		String label = request.getParameter(parameter);
		if(label == null) return "";
		else return label;
	}
	
	/**
	 * @param str
	 * @return
	 */
	public static String removeAccents(String str) {
	    str = Normalizer.normalize(str, Normalizer.Form.NFD);
	    str = str.replaceAll("[^\\p{ASCII}]", "");
	    return str;
	}
	
	public static boolean isInit(String s) {
		return s != null && !s.isEmpty();
	}
}