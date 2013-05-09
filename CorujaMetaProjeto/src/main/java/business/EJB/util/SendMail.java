package business.EJB.util;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import business.DAO.Config.ConfigurationDAO;
import business.exceptions.MailNotConfiguredException;
import business.exceptions.general.ConfigNotFoundException;
import business.exceptions.login.UnreachableDataBaseException;

/**
 * Classe para envio de email.
 * @author Vitor Kawai Sala
 *
 */
public class SendMail {

	private String account;	// Conta de email para o servidor
	private String password; // Senha da conta

	private String host; // Host SMTP 
	private String port; // Porta para conexão com o servidor de emails
	private String socketPort;	// Porta do socquet de conexão

	private ConfigurationDAO conf;

	public SendMail() throws UnreachableDataBaseException {
		conf = new ConfigurationDAO();
		try {
			// Tentativa para carregar informações.
			account = conf.getEntry("mailAccount").getValue();
			password = conf.getEntry("mailPassword").getValue();

			host = conf.getEntry("mailHost").getValue();
			port = conf.getEntry("mailPort").getValue();
			socketPort = conf.getEntry("mailSocketPort").getValue();
		} catch (ConfigNotFoundException e) {
			// Caso a classe está sendo chamado pela primeira vez, executar esta ação
			conf.addPropertie("mailAccount", "");
			conf.addPropertie("mailPassword", "");

			conf.addPropertie("mailHost", "smtp.gmail.com");
			conf.addPropertie("mailPort", "465");
			conf.addPropertie("mailSocketPort", "465");
		}
	}

	/**
	 * Envia um novo email.
	 * @param from - Remetente.
	 * @param to - Destinatário.
	 * @param subject - Assunto.
	 * @param msg - Corpo da menssagem.
	 * @throws MailNotConfiguredException Exceção caso o email não esteja configurando.
	 */
	public void send(String from, String to, String subject, String msg) throws MailNotConfiguredException {
		Properties props = new Properties();
		
		if(account.isEmpty() || password.isEmpty())	throw new MailNotConfiguredException("Necessário adicionar login e senha!");
		
		// Seta as configurações do email.
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.socketFactory.port", socketPort);
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");

		// Abre uma nova sessão para se conectar com o servidor SMTP.
		Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(account, password);
					}
				});

		try {
			// Cria a menssagem.
			Message message = new MimeMessage(session);
			try {
				message.setFrom(new InternetAddress(account, from));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));

			message.setSubject(subject);
			message.setText(msg);

			Transport.send(message);

			// System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}