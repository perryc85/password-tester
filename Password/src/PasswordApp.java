

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class PasswordApp {

	public static void main(String[] args) {
		//Read file for passwords
		String fileName = "C:\\Users\\Charles\\Documents\\SDET\\files\\passwords.txt";
		String [] passwords = new String[13];
		
		File file = new File(fileName);
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			for(int i=0; i<passwords.length; i++) {
				passwords[i] = br.readLine();
			}
		} catch (FileNotFoundException e1) {
			System.out.println("ERROR: could not open file.");
		} catch (IOException e) {
			System.out.println("ERROR: could not read file.");
		}
			
		for(String password : passwords) {
			System.out.println("\n******* " + password);
			boolean hasNumber = false;
			boolean hasLetter = false;
			boolean hasSpecialChar = false;

			
			for(int i = 0; i<password.length(); i++) {
				//condition 1: contains number
				if("0123456789".contains(password.substring(i, i+1))) {
					hasNumber = true;
					//condition 2: contains letter
				}else if("abcdefghijklmnopqrstuvwxyz".contains(password.substring(i, i+1).toLowerCase())) {
					hasLetter = true;
					//condition 3: contains special char
				}else if(".),(".contains(password.substring(i, i+1))) {
					hasSpecialChar = true;
				}else {
					try {
						throw new InvalidCharacterException (password.substring(i, i+1));
					} catch (InvalidCharacterException e) {
						e.toString();
					}
					
				}
				
			}
			
			//Test and Exception handling
			try {
				if(!hasNumber) {
					throw new NumberCriteriaException(password);
				}else if(!hasLetter) {
					throw new LetterCriteriaException(password);
				}else if(!hasSpecialChar) {
					throw new SpecialCharCriteriaException(password);
				}else {
					System.out.println("Valid password");
				}
			}catch (NumberCriteriaException | LetterCriteriaException | SpecialCharCriteriaException e) {
					System.out.println("Invalid password");
					System.out.println(e.toString());
			}
			
			}	
		}	
	}


class InvalidCharacterException extends Exception{
	
	String ch;
	
	public InvalidCharacterException(String ch) {
		this.ch = ch;
	}
	
	public String toString() {
		return "InvalidCharacterException: " + ch;
	}
}

class NumberCriteriaException extends Exception{
	String password;
	
	public NumberCriteriaException(String password) {
		this.password = password;
	}
	
	public String toString() {
		return "NumberCriteriaException: " + password;
	}
}

class LetterCriteriaException extends Exception{
	String password;
	
	public LetterCriteriaException(String password) {
		this.password = password;
	}
	
	public String toString() {
		return "LetterCriteriaException: " + password;
	}
}

class SpecialCharCriteriaException extends Exception{
	String password;
	
	public SpecialCharCriteriaException(String password) {
		this.password = password;
	}
	
	public String toString() {
		return "SpecialCharCriteriaException: " + password;
	}
}



