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

import persistence.PersistenceAccess;
import persistence.dto.DTO;
import persistence.dto.Profile;
import persistence.dto.UserAccount;
import persistence.util.PersistenceUtility;
import business.EJB.util.EJBUtility;

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

		log.info("Iniciando sistema Grão-Para...");
		PersistenceAccess pa = new PersistenceAccess();
		String[] profiles_names = {"default", "user", "admin"};

		List<DTO> profile;
		for (String p : profiles_names) {
			profile = pa.findEntity("from ProfileMO where profile = '" + p + "'");
			if(profile == null) {
				log.info("Profile '" + p + "' não encontrado, recriando..." );
				if(p.equals("user")) {
					pa.saveEntity(new Profile("user", new String[]{"default"}));
				}
				else if(p.equals("admin")) {
					pa.saveEntity(new Profile("admin", new String[]{"default","admin"}));
				}
			}
			else if(profile.size() > 1) {
				log.error("Profile '" + p + "' repetido no banco de dados, verificar imediatamente.");
			}
		}

		//TODO: MUDAR SENHA
		List<DTO> user = pa.findEntity("from UserAccountMO where email = 'admin@coruja.com'");
		if(user == null)
		{
			log.info("Criando usuário de admin...");
			pa.saveEntity(
					new UserAccount(
							"Admin", 
							(Profile) (pa.findEntity("from ProfileMO where profile = 'admin'").get(0)),
							"admin@graopara.com",
							EJBUtility.getHash("null","MD5")
					)
			);
		}
		else user = null;

		user = pa.findEntity("from UserAccountMO where email = 'user@coruja.com'");
		if(user == null)
		{
			log.info("Criando usuário de teste...");
			pa.saveEntity(
					new UserAccount(
							"Usuário Padrão Nível 1", 
							(Profile) (pa.findEntity("from ProfileMO where profile = 'user'").get(0)),
							"user1@graopara.com",
							EJBUtility.getHash("null","MD5")
					)
			);
			
		}
		else user = null;
		
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