package business.bean.user;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.Timer;

import persistence.EntityManager;
import persistence.model.UserAccount;
import persistence.model.exceptions.EntityPersistenceException;
import business.dao.login.UserDAO;
import business.exceptions.login.UnreachableDataBaseException;
import business.exceptions.login.UserNotFoundException;

/**
 * Classe singleton que verifica a validade das sessões de usuários no banco de
 * dados. <br>
 * O verificador somente irá executar uma ação quando der tempo para vencer
 * qualquer token desde a ultima verificação.<br>
 * <br>
 * Exemplo:<br>
 * Se o token vence a cada 10 minutos. O verificador só irá fazer a verificação
 * de 10 em 10 minutos.
 */
public enum TokenValidityChecker {

    INSTANCE;

    private EntityManager em;
    private UserDAO dao;
    private Timer timer;
    private int expireTime;

    /**
     * Limiar de verificação.<br>
     * O verificador irá sempre pegar todas as contas com a data do token abaixo
     * do limiar.
     */
    private String threshold;
    private SimpleDateFormat sdf;

    private TokenValidityChecker() {
	// Tempo para o token expirar em milisegundos. 1 minuto = 60000
	expireTime = (int) TimeUnit.DAYS.toMillis(1);
	dao = new UserDAO(em);
	// Seta formatação de data para verificação no banco.
	sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	// Seta limiar para a próxima verificação
	threshold = sdf.format(new GregorianCalendar().getTime());
	// Instancia um novo timer que executará o ActionListener a cada n
	// milisegundos
	timer = new Timer(expireTime, new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		try {
		    CheckIfExpired(); // Executa a verificação
		} catch (UserNotFoundException e1) {
		    // Caso não exista usuários com tokens vencidos, não fazer
		    // nada.
		}
		threshold = sdf.format(new GregorianCalendar().getTime());
	    }
	});

	if (expireTime != 0) {
	    timer.start();
	}
    }

    /**
     * Verifica se existe usuários com token vencido.<br>
     * Caso positivo, invalida todos os tokens vencidos.
     * 
     * @throws UserNotFoundException
     */
    private synchronized void CheckIfExpired() throws UserNotFoundException {
	try {
	    List<UserAccount> users = dao.listUsersWithExpiredTokens(threshold);
	    for (UserAccount u : users) {
		u.setTokenDate(null);
		u.setGeneratedToken(null);
		u.save(em);
	    }
	} catch (UnreachableDataBaseException e) {
	    e.printStackTrace();
	} catch (IllegalArgumentException e) {
	    e.printStackTrace();
	} catch (EntityPersistenceException e) {
	    e.printStackTrace();
	}
    }

    /**
     * Retorna o tempo de expiração em milisegundos.
     * 
     * @return
     */
    public int getExpireTime() {
	return expireTime;
    }

    /**
     * Seta um novo tempo de expiração.<br>
     * Setar para 0 para não expirar o token.
     * 
     * @param expireTime
     *            - Tempo, em milisegundos, de vida do token.<br>
     *            <b>0</b> para não expirar.
     */
    public void setExpireTime(int expireTime) {
	this.expireTime = expireTime;
	if (expireTime == 0) {
	    if (timer.isRunning()) {
		timer.stop();
	    }
	} else {
	    timer.setDelay(expireTime);
	    if (!timer.isRunning()) {
		timer.start();
		threshold = sdf.format(new GregorianCalendar().getTime());
	    }
	}
    }
}
