package utils;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class DateConverter {
	
	public static Date UserInputDateToSQLDate(String dateString) throws IllegalArgumentException {
		try {
			return Date.valueOf(dateString);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Please enter your birthdate in a valid format!");
		}
	}
		
	
	public static String SQLDateToUserInputString(Date sqlDate) {
		return sqlDate.toString();
	}
	
	
	public static String SQLDateToGermanDate(java.sql.Date sqlDate) throws ParseException {
		DateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		java.util.Date outputDate = inputDateFormat.parse(sqlDate.toString());

		DateFormat germanDateFormat = new SimpleDateFormat("dd.MM.yyyy");
		
		return germanDateFormat.format(outputDate).toString();
	}
}
