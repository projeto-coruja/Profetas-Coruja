package webview.servlet.account;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import persistence.exceptions.UpdateEntityException;
import webview.util.AlertsUtility;
import business.Bean.user.AdminBean;
import business.Bean.user.AuthBean;
import business.Bean.user.Permissions;
import business.Bean.user.RegisterUserBean;
import business.exceptions.DisallowedOperationException;
import business.exceptions.login.DuplicateUserException;
import business.exceptions.login.ProfileNotFoundException;
import business.exceptions.login.UnreachableDataBaseException;
import business.exceptions.login.UserNotFoundException;

/**
 * Servlet implementation class AccoutRemoveServlet
 */
@WebServlet("/doChangesToAccount")
public class AccountServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		AuthBean auth = new AuthBean();
		
		String action = request.getParameter("action");
		String email = request.getParameter("email");
		String newEmail = request.getParameter("newMail");
		String newPassword = request.getParameter("newPassword");
		String newProfile = request.getParameter("newProfile");
		if(auth.isLoggedIn(session)){	// Verifica se o usuário está logado.
			if(!newPassword.equals((String)request.getParameter("confPassword"))){
				AlertsUtility.alertAndRedirectHistory(response, "Password missmatch");
			}
			try {
				// Verifica se a mudança vai ser na própria conta (todos tem permissão para essa ação).
				if(action.equals("changeSelfAccountInformation") && auth.validateToken(session)){
					RegisterUserBean bean = new RegisterUserBean();
					bean.changeUserInformation((String) session.getAttribute(AuthBean.sessionUserMail), newEmail, newPassword);
					if(newEmail != null && !newEmail.isEmpty()){
						session.removeAttribute(AuthBean.sessionUserMail);
						session.setAttribute(AuthBean.sessionUserMail, newEmail);
					}
				}
				// Verifica se a mudança vai ser em outra conta (Precisa ter permissão especial).
				else if(action.equals("changeUserAccountInformation") && auth.allowedOperation(Permissions.userInfoUpdatePermission, session, true)){
					AdminBean admin = new AdminBean();
					admin.changeUserInformation(email, newEmail, newPassword, newProfile);	
				}
				AlertsUtility.alertAndRedirectPage(response, "Informações atualizadas com sucesso!", "public/index.jsp");
			} catch (UnreachableDataBaseException e) {
				e.printStackTrace();
			} catch (UserNotFoundException e) {
				AlertsUtility.alertAndRedirectHistory(response, e.getMessage());
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (UpdateEntityException e) {
				e.printStackTrace();
			} catch (DuplicateUserException e) {
				AlertsUtility.alertAndRedirectHistory(response, e.getMessage());
			} catch (ProfileNotFoundException e) {
				e.printStackTrace();
			} catch (DisallowedOperationException e) {
				AlertsUtility.alertAndRedirectPage(response, "Operação inválido!", "public/index.jsp");
			}
		}
		else{
			AlertsUtility.alertAndRedirectPage(response, "ERRO!", "public/index.jsp");
		}
	}

}