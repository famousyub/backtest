package com.omnia.app.util;

import java.util.Random;

/*
    Generates password according to parameters passed to generatePassword method
*/
public class PasswordGenerator {

	public static String generatePassword(int passwordLength, boolean upperAllowed, boolean lowerAllowed,
			boolean numbersAllowed, boolean specialCharactersAllowed) {

		// instance of random class
		Random random = new Random();

		String passwordCharacters = generatePasswordCharacters(upperAllowed, lowerAllowed, numbersAllowed,
				specialCharactersAllowed);

		StringBuilder result = new StringBuilder();

		if (passwordCharacters.isEmpty()) {
			return "";
		}

		for (int i = 0; i < passwordLength; i++) {

			// generate random value index from 0 to passwordCharacters.length())) - 1
			// returns the character at the specified index in the string passwordCharacters
			// append result to char value
			result.append(passwordCharacters.charAt(random.nextInt(passwordCharacters.length())));
		}
		return result.toString();
	}

	// Builds string to be used to generate passwords
	private static String generatePasswordCharacters(boolean upperAllowed, boolean lowerAllowed, boolean numbersAllowed,
			boolean specialCharactersAllowed) {
		String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String lowerCase = "abcdefghijklmnopqrstuvxyz";
		String numbers = "0123456789";
		String specialCharacters = "!$%@#";
		String availableCharacters = "";

		if (upperAllowed) {
			availableCharacters += upperCase;
		}
		if (lowerAllowed) {
			availableCharacters += lowerCase;
		}
		if (numbersAllowed) {
			availableCharacters += numbers;
		}
		if (specialCharactersAllowed) {
			availableCharacters += specialCharacters;
		}
		return availableCharacters;
	}
}
