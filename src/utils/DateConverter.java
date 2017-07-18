package utils;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateConverter {
	
	public static Date UserInputDateToJavaDate(String dateString) throws ParseException {
		DateFormat inputDateFormat = new SimpleDateFormat("dd-MM-yyyy");
		
		java.util.Date outputDate = inputDateFormat.parse(dateString);
		
		DateFormat sqlDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		return Date.valueOf(sqlDateFormat.format(outputDate));
	}
	
	
	public static String SQLDateToUserInputString(java.sql.Date sqlDate) throws ParseException {
		DateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		java.util.Date outputDate = inputDateFormat.parse(sqlDate.toString());
		
		DateFormat userInputFormat = new SimpleDateFormat("dd-MM-yyyy");
		
		return userInputFormat.format(outputDate).toString();
	}
	
	
	public static String SQLDateToGermanDate(java.sql.Date sqlDate) throws ParseException {
		DateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		java.util.Date outputDate = inputDateFormat.parse(sqlDate.toString());

		DateFormat germanDateFormat = new SimpleDateFormat("dd.MM.yyyy");
		
		return germanDateFormat.format(outputDate).toString();
	}
}
