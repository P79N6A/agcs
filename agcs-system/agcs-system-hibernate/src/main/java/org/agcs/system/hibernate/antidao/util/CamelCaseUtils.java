package org.agcs.system.hibernate.antidao.util;

public class CamelCaseUtils {
	private static final char SEPARATOR = '_';
	
	public static String toCamelCase(String s) {
	    if (s == null) {
	      return null;
	    }
	    s = s.toLowerCase();
	    StringBuilder sb = new StringBuilder(s.length());
	    boolean upperCase = false;
	    for (int i = 0; i < s.length(); i++) {
	      char c = s.charAt(i);
	      if (c == SEPARATOR) {
	        upperCase = true;
	      } else if (upperCase) {
	        sb.append(Character.toUpperCase(c));
	        upperCase = false;
	      } else {
	        sb.append(c);
	      }
	    }
	    return sb.toString();
	}

}
