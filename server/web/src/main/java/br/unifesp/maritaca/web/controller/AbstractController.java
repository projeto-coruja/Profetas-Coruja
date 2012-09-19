package br.unifesp.maritaca.web.controller;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import br.unifesp.maritaca.business.UserDTO;
import br.unifesp.maritaca.web.util.ConstantsWeb;

public abstract class AbstractController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	protected static final String MSG_SUCCESS		= "msg_success";
	protected static final String MSG_ERROR		= "msg_error";
	
	protected UserDTO getCurrentUser(HttpServletRequest request) {
		return (UserDTO)request.getSession().getAttribute(ConstantsWeb.CURRENT_USER);
	}
	
	protected void setCurrentUser(HttpServletRequest request, UserDTO maritacaUser){
		request.getSession().setAttribute(ConstantsWeb.CURRENT_USER, maritacaUser);
	}
}