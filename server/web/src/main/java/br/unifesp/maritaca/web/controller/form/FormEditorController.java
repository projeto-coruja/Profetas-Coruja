package br.unifesp.maritaca.web.controller.form;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.unifesp.maritaca.web.controller.AbstractController;

@Controller
public class FormEditorController extends AbstractController {

	private static final long serialVersionUID = 1L;
	
	@RequestMapping(value = "/form", method = RequestMethod.GET)
    public String showForm() {   
        return "form_editor";
    }

}