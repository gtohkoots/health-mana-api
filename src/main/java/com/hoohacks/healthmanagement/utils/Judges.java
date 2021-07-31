package com.hoohacks.healthmanagement.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Judges {
	public static boolean ssnValidity(String ssn) {
		String regex = "^(?!000|666)[0-8][0-9]{2}-(?!00)[0-9]{2}-(?!0000)[0-9]{4}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(ssn);
		return matcher.matches();
	}
}
