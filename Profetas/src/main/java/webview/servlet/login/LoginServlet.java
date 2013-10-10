package webview.servlet.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import webview.util.AlertsUtility;
import business.bean.user.AuthBean;
import business.exceptions.login.UnreachableDataBaseException;
import business.exceptions.login.UserNotFoundException;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/doLogin")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final String indexPage = "jsp/index.jsp";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
	super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	AuthBean auth = new AuthBean();
	// Recebe os parâmetros enviado via método post do HTML
	String user = request.getParameter("user");
	String password = request.getParameter("password");
	// Pega a sessão do usuário.
	HttpSession session = request.getSession();
	try {
	    // Autenticação.
	    auth.authenticate(user, password, session, AuthBean.HashedPwd);
	    // TODO: arrumar os redirecionamento.
	    if (!auth.isLoggedIn(session)) { // Verifica se a atenticação foi
					     // mal sucedido
		// Caso a autenticação não tenha sido bem sucedido, exibe uma
		// mensagem de erro na tela do usuário
		AlertsUtility
			.alertAndRedirectPage(response, "Problema na autenticação.", indexPage);
	    }
	    AlertsUtility.redirectOnly(response, indexPage); // Caso bem
							     // sucedido,
							     // somente
							     // redireciona o
							     // usuário
	} catch (UnreachableDataBaseException e) { // Caso ocorra algum problema
						   // com o hibernate.
	    e.printStackTrace();
	} catch (UserNotFoundException e) { // Caso o email não exista no banco
					    // de dados.
	    e.printStackTrace();
	    AlertsUtility.alertAndRedirectPage(response, "Usuário ou senha inválido.", indexPage);
	} catch (IllegalArgumentException e) { // Caso ocorra alguma entrada
					       // ilegal (ex. email inválido);
					       // nota: isso deve ser tratado
					       // antes do formulário ser
					       // enviado!
	    e.printStackTrace();
	} catch (UpdateEntityException e) { // Caso ocora algum problema para
					    // gravar a conta no banco de dados.
	    e.printStackTrace();
	}
    }
}
