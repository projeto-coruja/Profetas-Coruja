package general;

import java.util.Calendar;
import java.util.regex.Pattern;

import org.apache.commons.validator.routines.EmailValidator;

/**
 * A utility class with anything that we can use statically in any part of the project.
 * 
 * @author hueho
 *
 */

public class UtilityClass {
	
	
	/* Two patterns for use with notNum() and notAlphaNum() */
	private static final Pattern p = Pattern.compile("[^a-zA-Z0-9àáãâéêíóõôúçÀÁÃÂÉÊÍÓÕÔÚÇ -]");
	private static final Pattern q = Pattern.compile("[^0-9]");
	
	/* A EmailValidator instance (from the Commons Validator library) to check for valid emails */
	private static final EmailValidator email_check = EmailValidator.getInstance(false);
	
	/**
	 * Checks if a string contains a non-alphanumeric character, according to the compiled private
	 * {@code Pattern}. 
	 * 
	 * @param s a character sequence (such as a {@code String}).
	 * @return {@code true} if the parameter contains a non-alphanumeric character, {@code false} otherwise.
	 */
	public static boolean notAlphaNum(CharSequence s) {
		return p.matcher(s).find();
	}
	
	/**
	 * Checks if a string contains a non-numeric character.
	 * 
	 * @param s a character sequence (such as a {@code String}).
	 * @return {@code true} if the parameter contains a non-numeric character, {@code false} otherwise.
	 */
	public static boolean notNum(CharSequence s) {
		return q.matcher(s).find();
	}
	
	/** 
	 * Simple facade to invoke {@code EmailValidator.isValid()} method
	 * 
	 * @param email a {@code String} representing a email address
	 * @return {@code true} if the parameter is a valid email address, {@code false} otherwise.
	 * @see org.apache.commons.validator.routines.EmailValidator
	 */
	public static boolean validateEmail(String email) {
		return email_check.isValid(email);
	}
	
	/**
	 * Checks if a {@code String} is both initialized (not null) and not empty. Just a facade for
	 * {@code str == null || str.isEmpty()}
	 * 
	 * @param str the {@code String}
	 * @return {@code true} if the parameter is both not null and not empty, {@code false} otherwise
	 */
	public static boolean isInit(String str) {
		return str == null || str.isEmpty();
	}
	
	/**
	 * A small method for returning the current date as a SimpleDate object. Also a nice demonstration of
	 * how {@code SimpleDate} is stupid for this kind of stuff.
	 * @return a {@code SimpleDate} instance storing the current date.
	 */
	public static SimpleDate getNow() {
		Calendar c = Calendar.getInstance();
		return new SimpleDate(
				(short) c.get(Calendar.YEAR), 
				(short) c.get(Calendar.MONTH), 
				(short) c.get(Calendar.DATE));
	}
	
}
