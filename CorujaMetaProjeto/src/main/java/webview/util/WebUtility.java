package webview.util;

import java.io.IOException;
import java.text.Normalizer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;

import business.EJB.user.AuthBean;
import business.EJB.user.UserBean;
import business.exceptions.login.UnreachableDataBaseException;
import business.exceptions.login.UserNotFoundException;

public final class WebUtility {

	public static final String cookie_email = "email_graopara";
	public static final String cookie_session = "sessao_graopara";
	public static final String cookie_nome = "nome_graopara";
	public static final String cookie_status = "status_graopara";
	public static final String[] meses = {
		"Janeiro", "Fevereiro", "Março",
		"Abril", "Maio", "Junho", 
		"Julho", "Agosto", "Setembro", 
		"Outubro", "Novembro", "Dezembro"
	}; 

	public static final int cookie_expire = -1; //1 sessão dias
	
	/**
	 * 
	 * @param cookie_list
	 * @return
	 */
	public static UserBean cookieLogin(Cookie[] cookie_list) {
		if(cookie_list == null) return null;

		String email = null;
		String password = null;
		for(int i = 0; i < cookie_list.length; i++) {
			Cookie cookie = cookie_list[i];
			String cName = cookie.getName();
			if(cName.equals(WebUtility.cookie_email))
				email = cookie.getValue();
			if(cName.equals(WebUtility.cookie_session))
				password = cookie.getValue();
		}
		if(email != null && password != null) {
			try {
				UserBean result = AuthBean.validarLogin(email, password, AuthBean.HashedPwd);
				return result;
			} catch (UnreachableDataBaseException e) {
				e.printStackTrace();
				return null;
			} catch (UserNotFoundException e) {
				return null;
			}
		}
		return null;
	}

	/**
	 * 
	 * @param c_list
	 * @param c_name
	 * @return
	 */
	public static Cookie selectCookie(Cookie[] c_list, String c_name) {
		if(c_list == null) return null;

		for(int i = 0; i < c_list.length; i++) {
			Cookie cookie = c_list[i];
			String cName = cookie.getName();
			if(cName.equals(c_name)) return cookie;
		}
		return null;
	}
	
	/**
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static String strDiff(String str1, String str2) {
		int idx = str1.lastIndexOf(str2);
	    if (idx > -1) {
	      return str1.substring(str2.length());
	    }
	    else return str1;
	}

	/**
	 * 
	 * @param request
	 * @param out
	 * @throws IOException
	 */
	public static void printName(HttpServletRequest request, JspWriter out) throws IOException {
		Cookie name = selectCookie(request.getCookies(), WebUtility.cookie_nome);
		if(name != null) out.write("<label>" + name.getValue() + "!</label>");
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