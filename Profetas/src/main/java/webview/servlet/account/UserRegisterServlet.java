package webview.servlet.account;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webview.util.AlertsUtility;
import webview.util.WebUtility;
import business.bean.user.RegisterUserBean;
import business.exceptions.login.DuplicateUserException;
import business.exceptions.login.IncorrectLoginInformationException;
import business.exceptions.login.NoDefaultProfileException;
import business.exceptions.login.ProfileNotFoundException;

/**
 * Servlet implementation class CadastroServlet
 */
@WebServlet("/doRegister")
public class UserRegisterServlet extends HttpServlet {
    private final boolean autoApprove = true;
    private final String defaultProfile = "user";
    private final String indexPage = "jsp/index.jsp";
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRegisterServlet() {
	super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String nome = WebUtility.removeAccents(request.getParameter("name"));
	String email = request.getParameter("mail");
	String senha = request.getParameter("password");
	System.out.println(nome + "," + email + "," + senha);
	if (!senha.equals(request.getParameter("confPassword"))) {
	    AlertsUtility.alertAndRedirectHistory(response, "Senha inválida! Tente novamente.");
	} else {
	    RegisterUserBean register = new RegisterUserBean();
	    try {
		if (!autoApprove) {
		    register.addUser(email, senha, nome, null);
		    AlertsUtility.alertAndRedirectPage(response,
			    "Usuário adicionado! Aguarde a aprovação dos seus direitos de edição.",
			    indexPage);
		} else {
		    register.addUser(email, senha, nome, defaultProfile);
		    AlertsUtility.alertAndRedirectPage(response,
			    "Cadastro bem sucedido, faça o login.", indexPage);
		}
	    } catch (IncorrectLoginInformationException e) {
		AlertsUtility.alertAndRedirectPage(response,
			"Email inválido! Por favor tente novamente.", indexPage);
	    } catch (DuplicateUserException e) {
		AlertsUtility.alertAndRedirectPage(response,
			"Email já em uso! Por favor tente novamente.", indexPage);
	    } catch (ProfileNotFoundException e) {
		e.printStackTrace();
	    } catch (NoDefaultProfileException e) {
		e.printStackTrace();
	    }
	}
    }

}
