package webview.servlet.account;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webview.util.AlertsUtility;
import webview.util.WebUtility;
import business.Bean.user.RegisterUserBean;
import business.exceptions.login.DuplicateUserException;
import business.exceptions.login.IncorrectLoginInformationException;

/**
 * Servlet implementation class CadastroServlet
 */
@WebServlet("/doRegister")
public class UserRegisterServlet extends HttpServlet {

	private final String indexPage = "public/index.jsp";
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRegisterServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = WebUtility.removeAccents(request.getParameter("nome"));
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");

		if(!senha.equals(request.getParameter("confsenha"))){
			AlertsUtility.alertAndRedirectHistory(response, "Senha inválida! Tente novamente.");
		}
		else
		{
			RegisterUserBean register = new RegisterUserBean();
			try {
				register.addUser(email, senha, nome);
				AlertsUtility.alertAndRedirectPage(response, "Usuário adicionado! Aguarde a aprovação dos seus direitos de edição.", indexPage);
			} catch (IncorrectLoginInformationException e) {
				AlertsUtility.alertAndRedirectPage(response, "Email inválido! Por favor tente novamente.", indexPage);
			} catch (DuplicateUserException e) {
				AlertsUtility.alertAndRedirectPage(response, "Email já em uso! Por favor tente novamente.", indexPage);
			}
		}
	}

}
