package br.unifesp.profetas.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public abstract class AbstractController {

	public String getUsername() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String un;
        if (principal instanceof UserDetails) {
          un = ((UserDetails)principal).getUsername();
        } else {
          un = principal.toString();
        }
		System.out.println("un: " + un);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        if(!"anonymousUser".equals(username)) {
            return authentication.getName();
        } else{
            return null;
        }       
    }
}