package webview.servlet.account;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import webview.util.AlertsUtility;
import webview.util.WebUtility;
import business.Bean.user.AdminBean;
import business.Bean.user.AuthBean;
import business.Bean.user.Permissions;
import business.exceptions.DisallowedOperationException;
import business.exceptions.login.DuplicateUserException;
import business.exceptions.login.IncorrectLoginInformationException;
import business.exceptions.login.NoDefaultProfileException;
import business.exceptions.login.ProfileNotFoundException;
import business.exceptions.login.UnreachableDataBaseException;

/**
 * Servlet implementation class AdminCadServlet
 */
@WebServlet("/protected/admin/doAdminRegister")
public class AdminRegisterServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminRegisterServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		AuthBean auth = new AuthBean();
		String nome = WebUtility.removeAccents(request.getParameter("nome"));
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		String permissao = request.getParameter("permissao");

		if (!senha.equals(request.getParameter("confsenha"))) {
			
			AlertsUtility.alertAndRedirectPage(response, "Senha e verificação de senha diferente! Tente novamente.", "/GraoPara/protected/admin/cadUser.jsp");
			
		} else {
			
			AdminBean adminBean = new AdminBean();
			
			try {
				if(auth.allowedOperation(Permissions.addNewUserPermission, session, true)){
					adminBean.addUser(email, nome, senha, permissao);
					response.setContentType("text/html; charset=UTF-8");
				
					AlertsUtility.alertAndRedirectPage(response, "Usuário adicionado!", "/GraoPara/protected/admin/cadUser.jsp");
					
				}
				else{

					//AlertsUtility.alertAndRedirectPage(response, "Sem permissão para realizar a operação!", "/public/index.jsp");
				}

			} catch (UnreachableDataBaseException e) {
				
				AlertsUtility.alertAndRedirectPage(response, "Erro no banco de dados! Contate o suporte e tente novamente mais tarde.", "/GraoPara/protected/admin/cadUser.jsp");
				e.printStackTrace();
			} catch (IncorrectLoginInformationException e) {
				
				AlertsUtility.alertAndRedirectPage(response, "Email inválido!", "/GraoPara/protected/admin/cadUser.jsp");
			} catch (DuplicateUserException e) {
				
				AlertsUtility.alertAndRedirectPage(response, "Email já em uso!", "/GraoPara/protected/admin/cadUser.jsp");
			} catch (ProfileNotFoundException e) {
				e.printStackTrace();
			} catch (NoDefaultProfileException e) {
				e.printStackTrace();
			} catch (DisallowedOperationException e) {
				AlertsUtility.alertAndRedirectPage(response, "Operação inválido!", "public/index.jsp");
			}
		}
	}
	
}
