package by.krivorot.ishop.service.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
	
	private static final String EMAIL_REGEX ="([.[^@\\s]]+)@([.[^@\\s]]+)\\.([a-z]+)";
	private static final String EMPTY_STR="";

	public static boolean nullValidator(String email, String password) {

		if (email.equals(EMPTY_STR) || password.equals(EMPTY_STR)) {
			return false;
		}
		return true;
	}

	public static boolean registrationValidator(String firstPassword, String secondPassword) {

		if (firstPassword.equals(secondPassword)) {
			return true;
		}
		return false;
	}

	public static boolean emailValidator(String email) {
				 
		Pattern p = Pattern.compile(EMAIL_REGEX);
		 Matcher m = p.matcher(email);
		 
		 return m.matches();
			 
	}

}
