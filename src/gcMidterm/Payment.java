package gcMidterm;

import java.util.Scanner;

public class Payment {
	
	private static boolean getCash(String cash) {
		Scanner scnr = new Scanner(System.in);
		
		System.out.println("How much would you like to pay?($00.00)");
		String userAmount = scnr.nextLine();
		
		if (userAmount.matches("\\$\\d+\\.\\d{2}")) {
			return true;
		}
		return false;
	
	}

	private static boolean getCheck(String check) {
		Scanner scnr = new Scanner(System.in);
		
		System.out.println("What is your check number?");
		String checkNumber = scnr.nextLine();
		if (checkNumber.matches("\\d+")) {
			return true;
		}
		return false;
		
	}
	private static boolean getCredit(String credit) {
		Scanner scnr = new Scanner(System.in);
		
		System.out.println("What is your cc number? (xxxx-xxxx-xxxx-xxxx");
		String userCC = scnr.nextLine();
		
		System.out.println("Expiration? (mm/dd)");
		String userExpiration = scnr.nextLine();
		
		System.out.println("CVV?");
		String userCVV = scnr.nextLine();
		
		if (userCC.matches("\\d{4}\\-\\d{4}\\-\\d{4}\\-\\d{4}") 
				&& userExpiration.matches("\\d{2}\\/\\d{2}")
				&& userCVV.matches("\\d{3}")) {
					return true;
		}
		else return false;

		
	}

}
