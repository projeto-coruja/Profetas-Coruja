package webview.servlet.init;

import java.io.File;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;

import persistence.EntityManager;
import persistence.model.IdentifiedEntity;
import persistence.model.Profile;
import persistence.model.UserAccount;
import persistence.util.PersistenceUtility;
import business.Bean.user.TokenValidityChecker;
import business.Bean.util.Config;
import business.Bean.util.EJBUtility;
import business.exceptions.login.UnreachableDataBaseException;

/**
 * Servlet implementation class InitServlet
 */
public class InitServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static Log log = LogFactory.getLog(InitServlet.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InitServlet() {
		super();
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		initLogger(config);
		TokenValidityChecker.getInstance();

		log.info("Iniciando sistema Profetas...");
		EntityManager pa = new EntityManager();
		String[] profiles_names = {"user", "admin"};

		List<IdentifiedEntity> profile;
		for (String p : profiles_names) {
			profile = pa.find("from Profile where profile = '" + p + "'");
			if(profile == null) {
				log.info("Profile '" + p + "' não encontrado, recriando..." );
				if(p.equals("user")) {
					pa.save(new Profile("user", new String[]{"default"}, true));
				}
				else if(p.equals("admin")) {
					pa.save(new Profile("admin", new String[]{"default","admin"}, false));
				}
			}
			else if(profile.size() > 1) {
				log.error("Profile '" + p + "' repetido no banco de dados, verificar imediatamente.");
			}
		}

		//TODO: MUDAR SENHA
		List<IdentifiedEntity> user = pa.find("from UserAccount where email = 'admin@coruja.com'");
		if(user == null)
		{
			log.info("Criando usuário de admin...");
			pa.save(
					new UserAccount(
							"Admin", 
							(Profile) (pa.find("from Profile where profile = 'admin'").get(0)),
							"admin@coruja.com",
							EJBUtility.getHash("null","MD5"), 
							null,
							null
					)
			);
		}
		else user = null;

		user = pa.find("from UserAccount where email = 'user@coruja.com'");
		if(user == null)
		{
			log.info("Criando usuário de teste...");
			pa.save(
					new UserAccount(
							"Usuário Padrão", 
							(Profile) (pa.find("from Profile where profile = 'user'").get(0)),
							"user@coruja.com",
							EJBUtility.getHash("null","MD5"),
							null,
							null
					)
			);
			
		}
		else user = null;
		
		try {
			Config.getInstance();
		} catch (UnreachableDataBaseException e) {
			e.printStackTrace();
		}
		
		log.info("Finalizando inicialização...");
	}

	private void initLogger(ServletConfig config) {
		String logprop_config = config
				.getInitParameter("log4j.properties-location");
		ServletContext sc = config.getServletContext();

		if (logprop_config != null) {
			String webAppPath = sc.getRealPath("/");
			String real_logprop = webAppPath + logprop_config;
			File file_logprop = new File(real_logprop);
			if (file_logprop.exists()) {
				PropertyConfigurator.configureAndWatch(real_logprop);
				return;
			}
		}

		System.err
				.println("Erro iniciando o sistema de log do arquivo de configuração");
		System.err.println("Iniciando configuração de fallback básica...");
		BasicConfigurator.configure();
	}

	@Override
	public void destroy() {
		log.info("Encerrando o sistema...");
		log.info("Encerrando a SessionFactory do Hibernate...");
		PersistenceUtility.closeSessionFactory();
		log.info("Encerrando o logging...");
		log = null;
		//System.gc();
		LogFactory.releaseAll();
	}

}