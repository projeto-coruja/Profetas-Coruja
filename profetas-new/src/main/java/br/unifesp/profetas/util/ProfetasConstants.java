package br.unifesp.profetas.util;

public class ProfetasConstants {

	/* Profile	*/
	public static final int ID_PROFILE_ADMIN		= 1;
	public static final int ID_PROFILE_NEWUSER	= 7;
	
	public static final String PROFILE_ADMIN		= "ADMIN";
	public static final String PROFILE_NEWUSER	= "NEWUSER";
	
	public static final String REGISTERED = "2";
	/* Role		*/
	public static final String ROLE_NAME_ADMIN	= "ADMIN";
	public static final String ROLE_NAME_SAVE		= "SAVE";
	public static final String ROLE_NAME_SEARCH	= "SEARCH";
	
	public static final String LOCALE_DEFAULT		= "PT";
	public static final int ITEMS_PER_PAGE		= 10;
	//ISO 8601
	public final static String DATE_FORMAT_SHORT	="yyyy-MM-dd";
	
	
	public static final short MIN_FULLNAME_SIZE	= 2;
	public static final short MAX_FULLNAME_SIZE	= 25;
	public static final short MIN_PASSWORD_SIZE	= 5;
	public static final short MAX_PASSWORD_SIZE	= 20;
	public static final String EMAIL_REG_EXP 	= "^[\\w\\-]([\\.\\w])+[\\w]+@([\\w\\-]+\\.)+[a-zA-Z]{2,4}$";
	
}
