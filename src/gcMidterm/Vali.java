package gcMidterm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Vali {

	/**
	 * Get any valid integer.
	 */
	public static int getInt(Scanner scnr, String prompt) {
		// This approach uses exception handling.
		System.out.print(prompt);
		try {
			int num = scnr.nextInt();
			scnr.nextLine();
			return num;
		} catch (InputMismatchException e) {
			System.out.println("Enter a whole number, using digits.");
			scnr.nextLine();
			return getInt(scnr, prompt);
		}
	}

	/**
	 * Get any valid double.
	 */
	public static double getDouble(Scanner scnr, String prompt) {
		// This approach use "hasNext" look ahead.
		boolean isValid = false;
		do {
			System.out.print(prompt);
			isValid = scnr.hasNextDouble();
			if (!isValid) {
				scnr.nextLine();
				System.out.println("Enter a number, in digits.");
			}
		} while (!isValid);

		double number = scnr.nextDouble();
		scnr.nextLine();
		return number;
	}

	/**
	 * Get any string.
	 */
	public static String getString(Scanner scnr, String prompt) {
		// This approach uses exception handling.
		System.out.print(prompt);
		return scnr.nextLine();
	}

	/**
	 * Get any valid integer between min and max.
	 */
	public static int getInt(Scanner scnr, String prompt, int min, int max) {
		boolean isValid = false;
		int number;
		do {
			number = getInt(scnr, prompt);

			if (number < min) {
				isValid = false;
				System.out.println("The number must be at least " + min);
			} else if (number > max) {
				isValid = false;
				System.out.println("The number must not be larger than " + max);
			} else {
				isValid = true;
			}

		} while (!isValid);
		return number;
	}

	/**
	 * Get any valid double between min and max.
	 */
	public static double getDouble(Scanner scnr, String prompt, double min, double max) {
		boolean isValid = false;
		double number;
		do {
			number = getDouble(scnr, prompt);

			if (number < min) {
				isValid = false;
				System.out.println("The number must be at least " + min);
			} else if (number > max) {
				isValid = false;
				System.out.println("The number must not be larger than " + max);
			} else {
				isValid = true;
			}

		} while (!isValid);
		return number;
	}

	/**
	 * Get a string of input from the user that must match the given regex.
	 */
	public static String getStringMatchingRegex(Scanner scnr, String prompt, String regex) {
		boolean isValid = false;
		String input;
		do {
			input = getString(scnr, prompt);

			if (input.matches(regex)) {
				isValid = true;
			} else {
				System.out.println("Input must match the appropriate format.");
				isValid = false;
			}

		} while (!isValid);
		return input;
	}

	/**
	 * Get a date from user input in the format mm/dd/yyyy
	 */
	public static Date getDate(Scanner scnr, String prompt) {
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		format.setLenient(false); // <-- date format must match
		boolean isValid = false;
		Date date = null;
		String input;
		do {
			// Step 1: get the raw string
			input = getString(scnr, prompt);
			// Step 2: convert it to a date
			try {
				// format.parse throws a ParseException, which is a checked exception and MUST
				// be caught.
				date = format.parse(input);
				// If exception doesn't occur, it's valid.
				isValid = true;
			} catch (ParseException ex) {
				// If exception occurs, it's invalid.
				isValid = false;
				System.out.println("Enter a valid date in format mm/dd/yyyy.");
			}

		} while (!isValid);
		return date;
	}

	public static String stringIsKey(String string, Map<String, Object> map) {
		Set<String> keys = map.keySet();
		Iterator<String> iter = keys.iterator();
		String key;
		do {
			key = iter.next();
		} while (iter.hasNext() && !key.equalsIgnoreCase(string));
		return key;
	}

	public static boolean checkYes(String yes) {
		boolean isYes;
		// Method will accept yes or no. Basically gives user a direct way to affect the
		// finished boolean.
		if (yes.equalsIgnoreCase("y") || yes.equalsIgnoreCase("yes")) {
			isYes = true;
		} else {
			isYes = false;
		}
		return isYes;
	}

	// checks whether a date is a real, possible date. Seems redundant. Check
	// existent date classes later.
	public static boolean isDate(String userDate) {
		boolean isValid = true;
		if (userDate.matches("\\d\\d/\\d\\d/\\d{4}")) {
			String[] date = userDate.split("/");
			System.out.println(date[0] + " " + date[1] + " " + date[2]);
			if (Integer.parseInt(date[0]) <= 12 && Integer.parseInt(date[0]) > 0) {
				if (Integer.parseInt(date[0]) == 6 || Integer.parseInt(date[0]) == 4 || Integer.parseInt(date[0]) == 9
						|| Integer.parseInt(date[0]) == 11) {
					if (Integer.parseInt(date[1]) <= 30 && Integer.parseInt(date[1]) > 0) {
						isValid = true;
					} else {
						isValid = false;
					}
				} else if (Integer.parseInt(date[0]) == 1 || Integer.parseInt(date[0]) == 3
						|| Integer.parseInt(date[0]) == 5 || Integer.parseInt(date[0]) == 7
						|| Integer.parseInt(date[0]) == 8 || Integer.parseInt(date[0]) == 10
						|| Integer.parseInt(date[0]) == 12) {
					if (Integer.parseInt(date[1]) <= 31 && Integer.parseInt(date[1]) > 0) {
						isValid = true;
					} else {
						isValid = false;
					}
				} else if (Integer.parseInt(date[0]) == 2
						&& ((Integer.parseInt(date[2]) % 100 == 0) && ((Integer.parseInt(date[2]) % 400 != 0))
								|| ((Integer.parseInt(date[2]) % 4 == 0)))) {
					if (Integer.parseInt(date[1]) <= 29 && Integer.parseInt(date[1]) > 0) {
						isValid = true;
					} else {
						isValid = false;
					}
				} else if (Integer.parseInt(date[0]) == 2) {
					if (Integer.parseInt(date[1]) <= 28 && Integer.parseInt(date[1]) > 0) {
						isValid = true;
					} else {
						isValid = false;
					}
				} else {
					isValid = false;
				}
			} else {
				isValid = false;
			}

		} else {
			isValid = false;
		}
		return isValid;
	}

	public static int enumInArray(Enum ar[], Enum num) {
		int index = -1;
		for (int i = 0; i < ar.length; i++) {
			if (ar[i] == num) {
				index = i;
			}
		}
		return index;
	}

	public static int stringInArrayAt(String ar[], String str) {
		int index = -1;
		for (int i = 0; i < ar.length; i++) {
			if (ar[i].equals(str)) {
				index = i;
			}
		}
		return index;
	}
}