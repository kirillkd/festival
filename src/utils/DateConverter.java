package utils;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class DateConverter {

	/**
	 * Converts a date entered on the HTML page according to RFC 3339 standard
	 * to the SQL Date Type.
	 * 
	 * @param dateString the string entered by the user
	 * @return the date as java.sql.Date
	 * @throws IllegalArgumentException
	 */
	public static Date UserInputDateToSQLDate(String dateString) throws IllegalArgumentException {
		try {
			return Date.valueOf(dateString);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Please enter your birthdate in a valid format!");
		}
	}

	/**
	 * Converts a SQL Date to a string.
	 *
	 * @param sqlDate the date as java.sql.Date
	 * @return the date as string
	 */
	public static String SQLDateToUserInputString(Date sqlDate) {
		return sqlDate.toString();
	}

	/**
	 * Converts a SQL Date to a German date string. Useful for improving readability
	 * and user experience.
	 * @param sqlDate the date as java.sql.Date
	 * @return the date in German date format
	 * @throws ParseException
	 */
	public static String SQLDateToGermanDate(Date sqlDate) throws ParseException {
		DateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		java.util.Date outputDate = inputDateFormat.parse(sqlDate.toString());

		DateFormat germanDateFormat = new SimpleDateFormat("dd.MM.yyyy");
		
		return germanDateFormat.format(outputDate).toString();
	}
}
