package br.unifesp.maritaca.business.util;

/**
 * This class is to set all the constants used in business layer
 * 
 */
public class ConstantsBusiness {

	public static final String ALL_USERS 		= "all_users"; 	// public group
	public static final String WS_USER_KEY 	= "userKey";	
	public static final String EMAIL_REG_EXP 	= "^[\\w\\-]([\\.\\w])+[\\w]+@([\\w\\-]+\\.)+[a-zA-Z]{2,4}$";
	public static final String ENCODING_UTF8 	= "UTF-8";
	
	/* App Mobile */
	public static final String CONF_FILE_NAME			= "configuration.properties";
	public static final String MARITACA_MOBILE_PATH 	= "maritaca_mobile_path";
	public static final String MOB_SCRIPT_LOCATION	= "scripts/maritaca.sh";
	public static final String MOB_RELEASE_SCRIPT_LOCATION	= "scripts/maritaca_release.sh";
	public static final String MARITACA_MOBILE_FOLDER		= "maritaca-mobile";
	public static final String MARITACA_MOBILE_PROJECTS	= "apps/";
	public static final String MARITACA_URI_SERVER		= "maritaca_uri_server";
	
	public static final String ANDROID_SDK_PATH 		= "android_sdk_path";
	public static final String MOB_FORM_XML_PATH		= "/maritaca-mobile/assets/sample.xml";
	public static final String MOB_FORM_LOGO_PATH		= "/maritaca-mobile/res/drawable-hdpi/form_logo.png";
	public static final String MOB_BIN_PATH			= "/maritaca-mobile/bin/";	
	public static final String MOB_MIMETYPE			= "application/vnd.android.package-archive";
}