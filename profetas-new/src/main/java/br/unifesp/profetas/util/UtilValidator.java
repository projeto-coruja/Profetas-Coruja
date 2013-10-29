package br.unifesp.profetas.util;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.joda.time.DateTime;

public class UtilValidator {
	
	public static boolean validateNotEmptyField(String fieldName){
		if(fieldName == null)
			return false;
		String field = fieldName.trim();
		if(field.length() > 0)
			return true;
		return false;
	}
	public static boolean validateField(String fieldName, short minSize, short maxSize){
		if(fieldName == null)
			return false;
		String field = fieldName.trim();
		if(field.length() < minSize || field.length() > maxSize)
			return false;
		return true;
	}
	public static boolean validateYearField(String fieldName){
		if(fieldName == null)
			return false;
		try{
			int year = Integer.parseInt(fieldName);
			return true;
		} catch(Exception e){
			return false;
		}
	}
	public static Date getDateFromString(String strDate){
		try{
			if(strDate != null && !"".equals(strDate.trim())){
				 DateTime jdt = new DateTime(strDate);
				 return jdt.toDate();
			}
			return null;
		} catch(Exception e){
			return null;
		}
	}
	public static boolean validateEmail(String email){
		Pattern pattern = Pattern.compile(ProfetasConstants.EMAIL_REG_EXP);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
}