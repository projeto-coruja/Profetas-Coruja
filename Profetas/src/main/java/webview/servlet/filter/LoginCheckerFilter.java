package webview.servlet.filter;

import java.io.IOException;
import java.util.GregorianCalendar;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import business.Bean.user.AuthBean;
import business.exceptions.login.UnreachableDataBaseException;
import business.exceptions.login.UserNotFoundException;

/**
 * Servlet Filter implementation class RootFilter
 */
@WebFilter("/*")
public class LoginCheckerFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginCheckerFilter() {
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest)request).getSession();
		AuthBean auth = new AuthBean();
		if(auth.isLoggedIn(session)){
			long time = new GregorianCalendar().getTimeInMillis();
			long created = (Long) session.getAttribute(AuthBean.sessionUserAccessTokenCreatedTime);
			int expire = (Integer) session.getAttribute(AuthBean.sessionUserAccessTokenLifeTime);
			
			if(expire != 0 && (time - created) >= expire){
				try {
					auth.logOut(session);
				} catch (UserNotFoundException e) {
					e.printStackTrace();
				} catch (UnreachableDataBaseException e) {
					e.printStackTrace();
				}
			}
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
