package business.Bean.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.Timer;

import persistence.dto.DTO;
import persistence.dto.UserAccount;
import persistence.exceptions.UpdateEntityException;

import business.DAO.login.UserDAO;
import business.exceptions.login.UnreachableDataBaseException;
import business.exceptions.login.UserNotFoundException;

/**
 * Classe singleton que verifica a validade das sessões de usuários no banco de dados. <br>
 * O verificador somente irá executar uma ação quando der tempo para vencer qualquer token desde a ultima verificação.<br>
 * <br>
 * Exemplo:<br>
 * Se o token vence em 10 minutos. O verificador só irá fazer a verificação de 10 em 10 minutos.
 */
public class TokenValidityChecker {
	
	private UserDAO dao;
	private Timer timer;
	private int expireTime;
	/**
	 * Limiar de verificação.<br>
	 * O verificador irá sempre pegar todas as contas com a data do token abaixo do limiar. 
	 */
	private String threshold;
	private SimpleDateFormat sdf;
	
	private static TokenValidityChecker instance; // Instancia do verificador.
	
	private TokenValidityChecker(){

		/*
		 * Tempo para o token expirar em milisegundos.
		 * 1 minuto = 60000
		 */
		expireTime = 60000;
		//=====
		dao = new UserDAO();
		sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"); // Seta formatação de data para a verificação no banco.
		threshold = sdf.format(new GregorianCalendar().getTime());	// Seta a limiar para a próxima verificação.
		
		timer = new Timer(60000, new ActionListener() { // Intancia um novo timer, o timer executará o action listener a cada n milisegundos.  
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					CheckIfExpired();	// Executa a verificação
				} catch (UserNotFoundException e1) { // Caso não exista usuários com token vencidos, não fazer nada.
				}
				threshold = sdf.format(new GregorianCalendar().getTime()); // Seta o novo limiar
			}
		});
		timer.start();
	}
	/**
	 * Pega a instancia do verificador.
	 * @return instance - Instancia do verificador.
	 */
	public static TokenValidityChecker getInstance(){
		if(instance == null)	instance = new TokenValidityChecker();
		return instance;
	}
	/**
	 * Verifica se existe usuários com token vencido.<br>
	 * Caso positivo, invalida todos os tokens vencidos.
	 * @throws UserNotFoundException
	 */
	private void CheckIfExpired() throws UserNotFoundException{
		try {
			List<DTO> users = dao.listUsersWithExpiredTokens(threshold);
			for(DTO dto : users){
				((UserAccount)dto).setTokenDate(null);
				((UserAccount)dto).setGeneratedToken(null);
				dao.updateUser((UserAccount)dto);
			}
		} catch (UnreachableDataBaseException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (UpdateEntityException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Retorna o tempo de expiração em milisegundos.
	 * @return
	 */
	public int getExpireTime() {
		return expireTime;
	}

	/**
	 * Seta um novo tempo de expiração.<br>
	 * Setar para 0 para não expirar o token.
	 * @param expireTime - Tempo, em milisegundos, de vida do token.<br> <b>0</b> para não expirar.
	 */
	public void setExpireTime(int expireTime) {
		this.expireTime = expireTime;
		if(expireTime == 0){
			if(timer.isRunning())	timer.stop();
		}
		else{
			timer.setDelay(expireTime);
			if(!timer.isRunning()){
				timer.start();
				threshold = sdf.format(new GregorianCalendar().getTime());
			}
		}
	}
}
