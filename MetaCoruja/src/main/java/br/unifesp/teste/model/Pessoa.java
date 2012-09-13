package br.unifesp.teste.model;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findPessoasByNomeEquals", "findPessoasByOnlineNot" })
public class Pessoa {

    private String Nome;

    private Boolean Online;
}
