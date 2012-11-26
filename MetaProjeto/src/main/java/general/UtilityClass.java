package general;

import java.util.regex.Pattern;

import org.apache.commons.validator.routines.EmailValidator;

public class UtilityClass {
	
	private static final Pattern p = Pattern.compile("[^a-zA-Z0-9àáãâéêíóõôúçÀÁÃÂÉÊÍÓÕÔÚÇ -]");
	private static final Pattern q = Pattern.compile("[^0-9]");
	
	private static final EmailValidator email_check = EmailValidator.getInstance(false);
	
	public static boolean notAlphaNum(String s) {
		return p.matcher(s).find();
	}
	
	public static boolean notNum(String s) {
		return q.matcher(s).find();
	}
	
	public static boolean validateEmail(String email) {
		return email_check.isValid(email);
	}

	public static boolean isInit(String str) {
		return str == null || str.isEmpty();
	}
	
}
