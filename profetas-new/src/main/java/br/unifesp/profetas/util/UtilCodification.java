package br.unifesp.profetas.util;

import java.security.MessageDigest;

import org.apache.commons.lang.RandomStringUtils;

public class UtilCodification {
	
	public static final String randomString() {
		return RandomStringUtils.randomAlphanumeric(ProfetasConstants.STRING_LENGTH);
	}
	
	private static String hex(byte[] array) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
            sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString();
    }

	/**
	 * 
	 * @param string value to encrypt
	 * @param type MD5 or SHA1
	 * @return string encrypted
	 */
    public static String encryptHex(String value, String type) {
        try {
            MessageDigest md = MessageDigest.getInstance(type);
            return hex(md.digest(value.getBytes("CP1252"))); //Windows-1252
        } catch (Exception ex) {
        	throw new RuntimeException(ex);
        }
    }
}