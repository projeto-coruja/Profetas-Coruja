package br.unifesp.maritaca.web.controller.form;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.unifesp.maritaca.web.controller.AbstractController;

@Controller
public class FormListerController extends AbstractController {

	private static final long serialVersionUID = 1L;
	
	@RequestMapping(value = "/forms", method = RequestMethod.GET)
    public String showForms() {   
        return "form_lister";
    }

}