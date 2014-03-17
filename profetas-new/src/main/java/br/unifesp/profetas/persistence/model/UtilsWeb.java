package br.unifesp.profetas.persistence.model;

import javax.servlet.http.HttpServletRequest;

public class UtilsWeb {

	public static String buildServerAddressUrl(HttpServletRequest request) {
		return request.getScheme() + "://" + request.getServerName();// + ":" + request.getServerPort();
    }
	
	public static String buildContextUrl(HttpServletRequest request) {
		return buildServerAddressUrl(request) + request.getContextPath();
	}
}