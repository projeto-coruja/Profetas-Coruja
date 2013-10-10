package business.bean.util;

import java.util.regex.Pattern;

/**
 * Classe para utilização e validação de strings utilizando expressão regular.
 */
public class Regex {

    private Pattern p;

    private static final Pattern w = Pattern
	    .compile("[^a-zA-Z0-9àáãâäèéẽêëìíĩîïòóõôöùúũûüçÀÁÃÂÄÈÉẼÊËÌÍĨÎÏÒÓÕÔÖÙÚŨÛÜÇ: -]");
    private static final Pattern n = Pattern.compile("[^0-9]");

    /**
     * Verifica se a string é uma palavra.
     * 
     * @param s
     *            - String a ser verificado.
     * @return <b>true</b> se a string for uma palavra.<br>
     *         <b>false</b> caso contrário.
     */
    public static boolean findAN(String s) {
	return w.matcher(s).find();
    }

    /**
     * Verifica se a string é um número.
     * 
     * @param s
     *            - String a ser verificado.
     * @return <b>true</b> se a string for um número.<br>
     *         <b>false</b> caso contrário.
     */
    public static boolean findN(String s) {
	return n.matcher(s).find();
    }

    /**
     * Construtor que irá forçar a utilização das expressões pré-definidas.
     */
    public Regex() {
    }

    /**
     * Construtor.
     * 
     * @param pattern
     *            - Expressão regular que será utilizado para a validação.
     */
    public Regex(String pattern) {
	if (pattern.charAt(0) != '^') {
	    pattern = "^" + pattern;
	}
	if (pattern.charAt(pattern.length() - 1) != '$') {
	    pattern = pattern + "$";
	}
	// System.out.println(pattern);
	p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
    }

    /**
     * Verifica se uma string está de acordo com a expressão regular.<br>
     * <br>
     * Caso nenhum padrão seja passado anteriormente, o método irar utilizar as
     * expressões pré-definidas.
     * 
     * @param string
     *            - String a ser verificado.
     * @return <b>true</b> se a string estiver de acordo com a expressão
     *         regular.<br>
     *         <b>false</b> caso contrário.
     */
    public boolean check(String string) {
	if (p == null || p.pattern() == null) {
	    return findAN(string) || findN(string);
	}
	return p.matcher(string).find();
    }

    /**
     * Retorna a expressão regular.
     * 
     * @return String - Expressão regular em utilização.
     */
    public String getPattern() {
	return p.pattern();
    }

    /**
     * Seta uma nova expressão regular.
     * 
     * @param newPattern
     *            - Expressão regular.
     */
    public void setPattern(String newPattern) {
	if (newPattern.charAt(0) != '^') {
	    newPattern = "^" + newPattern;
	}
	if (newPattern.charAt(newPattern.length() - 1) != '$') {
	    newPattern = newPattern + "$";
	}
	p = Pattern.compile(newPattern, Pattern.CASE_INSENSITIVE);
    }

}
