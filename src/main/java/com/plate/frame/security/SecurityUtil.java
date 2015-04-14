package com.plate.frame.security;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author rainsponsor
 *
 */
public class SecurityUtil {

	/**
	 * 检查用户密码的复杂程度
	 * 
	 * @param password
	 */
	public long checkPasswordComplex(String password) {
		Pattern checknum = Pattern.compile("[0-9]");
		Pattern checkchar = Pattern.compile("[a-z]");
		Pattern checkcharA = Pattern.compile("[A-Z]");
		Pattern tchar = Pattern.compile("(.[^a-z0-9])");
		Matcher m1 = checknum.matcher(password);
		Matcher m2 = checkchar.matcher(password);
		Matcher mA = checkcharA.matcher(password);
		Matcher m3 = tchar.matcher(password);
		boolean found1 = m1.find();
		boolean found2 = m2.find();
		boolean foundA = mA.find();
		boolean found3 = m3.find();
		if (found1) {
			return 0; // System.out.println("输入的密码弱！");
		}
		if (found2 || foundA) {
			return 1; // System.out.println("输入的密码中！");
		}
		if (found3) {
			return 2; // System.out.println("输入的密码强！");
		} else {
			return 4;
		}
		// System.out.println("输入的密码过于简单，存在不安全！");
	}

}
