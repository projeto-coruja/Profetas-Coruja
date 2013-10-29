package br.unifesp.profetas.web.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TutorialController {
	
	@RequestMapping(value = "/tutorial", method = RequestMethod.GET)
    public String showView() {
        return "page_tutorial";
    }
}