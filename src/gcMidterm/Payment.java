package gcMidterm;

import java.util.Scanner;

public class Payment {
	private static Scanner scnr = new Scanner(System.in);

	public static Double getCash(Double total) {

//		System.out.println("How much would you like to pay?($00.00)");
//		String userAmount = scnr.nextLine();
		double userPay = Vali.getDouble(scnr, "How much would you like to pay? Total: $" + total);
		double change = userPay - total;
		return change;

		// if (userAmount.matches("\\$\\d+\\.\\d{2}")) {

	}

	public static boolean getCheck() {

		System.out.println("What is your check number?");
		String checkNumber = scnr.nextLine();
		if (checkNumber.matches("\\d+")) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean getCredit() {

		System.out.println("What is your cc number? (xxxx-xxxx-xxxx-xxxx");
		String userCC = scnr.nextLine();

		System.out.println("Expiration? (mm/dd)");
		String userExpiration = scnr.nextLine();

		System.out.println("CVV?");
		String userCVV = scnr.nextLine();

		if (userCC.matches("\\d{4}\\-\\d{4}\\-\\d{4}\\-\\d{4}") && userExpiration.matches("\\d{2}\\/\\d{2}")
				&& userCVV.matches("\\d{3}")) {
			return true;
		} else {
			return false;
		}
	}
		
	public static boolean getApplePay() {
		return true;
	}
	

	}


