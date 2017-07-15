package utils;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class StringToSQLDate {
	
	public static Date convert(String dateString) throws ParseException {
		DateFormat inputDateFormat = new SimpleDateFormat("dd-MM-yyyy");
		
		java.util.Date outputDate = inputDateFormat.parse(dateString);
		
		DateFormat sqlDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		return Date.valueOf(sqlDateFormat.format(outputDate));
	}
}
