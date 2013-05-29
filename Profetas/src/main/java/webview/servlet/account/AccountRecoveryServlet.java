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
import business.Bean.user.AuthBean;
import business.Bean.user.RegisterUserBean;
import business.Bean.util.SendMail;
import business.exceptions.MailNotConfiguredException;
import business.exceptions.login.UnreachableDataBaseException;
import business.exceptions.login.UserNotFoundException;

/**
 * Servlet implementation class AccountRecoveryServlet
 */
@WebServlet("/doPasswordRecovery")
public class AccountRecoveryServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountRecoveryServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AuthBean auth = new AuthBean();
		HttpSession session = request.getSession();
		String email = request.getParameter("email"); 
		String sessionToken = request.getParameter("sessionid"); 
		if(email == null || email.isEmpty()){
			AlertsUtility.alertAndRedirectHistory(response, "Nenhum email fornecido.");
		}
		else{
			session.setAttribute("userMail", email);
			session.setAttribute("userAccessToken", sessionToken);
		}
		try {
			SendMail mail = new SendMail();
			if(sessionToken == null || sessionToken.isEmpty()){
				sessionToken = auth.createRecoveryToken(email);
				
				// Mandar pro email o link de autenticação.
				// mail.send(from, to, subject, msg);
				
				// Teste
				AlertsUtility.alertAndRedirectHistory(response, "link: http://localhost:8080/Profetas/doPasswordRecovery?email="+ email +"&sessionid="+ sessionToken); // DEBUG!
			}
			else{
				if(auth.validateToken(session)){
					RegisterUserBean bean = new RegisterUserBean();
					// Gera uma nova senha
					String password = bean.recuperarSenha(email);
					
					//Teste
					AlertsUtility.alertAndRedirectHistory(response, "senha: "+ password); // DEBUG!
				}
				//AlertsUtility.alertAndRedirectHistory(response, "Nova senha gerado para "+ email +": "+ newPassword);
			}
			
		} catch (UnreachableDataBaseException e) {
			
			AlertsUtility.alertAndRedirectHistory(response, "Problema ao se conectar ao banco de dados.");
			e.printStackTrace();
		} catch (UserNotFoundException e) {
			
			AlertsUtility.alertAndRedirectHistory(response, "Usuário não encontrado.");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (UpdateEntityException e) {
			e.printStackTrace();
		} catch (MailNotConfiguredException e) {
		}
	}
	
}