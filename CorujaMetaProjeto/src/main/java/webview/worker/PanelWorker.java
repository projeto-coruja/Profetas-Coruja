package webview.worker;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;

import persistence.dto.DTO;
import persistence.dto.Profile;
import persistence.dto.UserAccount;
import webview.util.WebUtility;
import business.EJB.user.AdminBean;
import business.EJB.user.SearchUserBean;
import business.exceptions.login.ProfileNotFoundException;
import business.exceptions.login.UnreachableDataBaseException;
import business.exceptions.login.UserNotFoundException;


public class PanelWorker {
	/**
	 * 	Tipo de enconde <br>
	 *	US-ASCII	 	Seven-bit ASCII, a.k.a. ISO646-US, a.k.a. the Basic Latin block of the Unicode character set<br>
	 *	ISO-8859-1   	ISO Latin Alphabet No. 1, a.k.a. ISO-LATIN-1<br>
	 *	UTF-8 			Eight-bit UCS Transformation Format<br>
	 *	UTF-16BE 		Sixteen-bit UCS Transformation Format, big-endian byte order<br>
	 *	UTF-16LE 		Sixteen-bit UCS Transformation Format, little-endian byte order<br>
	 *	UTF-16 			Sixteen-bit UCS Transformation Format, byte order identified by an optional byte-order mark<br>
	 */
	
	private static final String encode = "UTF-8";
	
		
	public static void listAllUsers(HttpServletRequest request, JspWriter out) throws IOException{
		Cookie d_email = WebUtility.selectCookie(request.getCookies(), WebUtility.cookie_email);
		String c_email;		
		if(d_email == null)
			c_email = null;
		else
			c_email = d_email.getValue();		
		SearchUserBean busca = new SearchUserBean();
		List<DTO> users = null;	  		
		try {
			users = busca.listUsers();				
			for(DTO u : users){
				UserAccount user = (UserAccount) u;				
				if(!user.getEmail().equals(c_email)){
					out.println("<tr>");
					out.println("<td> <label for=\"identificacao\" class=\"labelExibe\">" + user.getName() + "</label> </td>");
					out.println("<td> <label for=\"identificacao\" class=\"labelExibe\">" + user.getEmail() + " </label> </td>");
					out.println("<td> <label for=\"identificacao\" class=\"labelExibe\">" + user.getProfile().getProfile() + "</label> </td>");
					out.println("<td>"
							+ "<a href=\"/GraoPara/protected/admin/editarUsuario.jsp?"
							+ "paramName=" + user.getName()
							+"&paramEmail="+user.getEmail()+"\" ><img src=\"/GraoPara/images/edit.png\" title=\"Editar\" alt=\"Editar\" /></a>" 
							+ "<a href=\"javascript:confirmAction('Tem certeza que deseja remover o usuário "+user.getEmail()+"?','/GraoPara/protected/admin/removeAccount?" 
							+ "email=" + user.getEmail()+"&tab=2')\" ><img src=\"/GraoPara/images/remove.png\" title=\"Remover\" alt=\"Remover\" /></a>"
							+ "</td>");
					out.println("</tr>");
					out.println("</tr>");
				}
			}				
		} catch (UnreachableDataBaseException e) {
			out.write("<script>");  
			out.write("alert('Problemas ao acessar o banco de dados. Contate o suporte técnico e tente novamente mais tarde.');"); 
			out.write("</script>");
			e.printStackTrace();
		} catch (UserNotFoundException e) {
			out.println("<td colspan=\"4\"><label class=\"labelExibe\">Nenhum usuário encontrado.</label></td>");
		}
	}
	
	public static void listAllNewUsers(HttpServletRequest request, JspWriter out) throws IOException{
		SearchUserBean busca = new SearchUserBean();
		List<DTO> users = null;		
		try {
			users = busca.listUsers();			
			for(DTO u : users){
				UserAccount user = (UserAccount) u;				
				if(user.getProfile().getProfile().equals("default")){
					out.println("<tr>");
					out.println("<td> <label for=\"identificacao\" class=\"labelExibe\">" + user.getName() + "</label> </td>");
					out.println("<td> <label for=\"identificacao\" class=\"labelExibe\">" + user.getEmail() + " </label> </td>");
					out.println("<td> <label for=\"identificacao\" class=\"labelExibe\">" + user.getProfile().getProfile() + "</label> </td>");
					out.println("<td>"
						+ "<a href=\"/GraoPara/protected/admin/approveAccount?" 
							+ "email=" + user.getEmail()
							+ "&nivel=" + 1
							+ " \"><img src=\"/GraoPara/images/approve.png\" title=\"Aprovar como usuário comum\" alt=\"Aprovar como usuário comum\" /></a>"
						+ "<a href=\"/GraoPara/protected/admin/approveAccount?" 
							+ "email=" + user.getEmail()
							+ "&nivel=" + 2
							+ " \"><img src=\"/GraoPara/images/approve2.png\" title=\"Aprovar como usuário avançado\" alt=\"Aprovar como usuário avançado\" /></a>"
						+ "<a href=\"/GraoPara/protected/admin/editarUsuario.jsp?"
							+ "paramName=" + user.getName()
							+"&paramEmail="+user.getEmail()+"\" ><img src=\"/GraoPara/images/edit.png\" title=\"Editar\" alt=\"Editar\" /></a>" 
						+ "<a href=\"javascript:confirmAction('Tem certeza que deseja remover o usuário "+user.getEmail()+"?','/GraoPara/protected/admin/removeAccount?" 
							+ "email=" + user.getEmail()+"&tab=2')\" ><img src=\"/GraoPara/images/remove.png\" title=\"Remover\" alt=\"Remover\" /></a>"
							+ "</td>");
					out.println("</tr>");
				}
			}				
		} catch (UnreachableDataBaseException e) {
			out.write("<script>");  
			out.write("alert('Problemas ao acessar o banco de dados. Contate o suporte técnico e tente novamente mais tarde.');"); 
			out.write("</script>");
			e.printStackTrace();
		} catch (UserNotFoundException e) {
			out.println("<td colspan=\"4\"><label class=\"labelExibe\">Nenhum usuário novo encontrado.</label></td>");
		}
	}
	
	public static void listAllAvailableProfile(HttpServletRequest request, JspWriter out) throws IOException{
		AdminBean adm = new AdminBean();
		List<DTO> list;		
		try {
			list = adm.getAllAvailableProfiles();			
			for(DTO dto : list){
				Profile profile = (Profile) dto;
				out.println("<option value=\""+ profile.getProfile() +"\">"+ profile.getProfile() + "</option>");
			}
		} catch (UnreachableDataBaseException e) {
			e.printStackTrace();
		} catch (ProfileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
