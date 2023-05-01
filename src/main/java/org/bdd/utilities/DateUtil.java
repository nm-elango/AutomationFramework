package org.bdd.utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DateUtil {

	private static Logger Log = LogManager.getLogger();

	public static String convertDateIntoString(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	public static Date convertStringIntoDate(String date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(date);
		} catch (ParseException pe) {
			Log.warn(pe.getMessage());
		}
		return null;
	}

	public static boolean isDate(String date, String format) {
		Date dateVal = null;
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			dateVal = sdf.parse(date);
		} catch (ParseException pe) {
			Log.warn(pe.getMessage());
		}
		return dateVal != null;
	}

	/**
	 * @param format (Date or time format, example DDMMYYYY or HH:MM)
	 * @return
	 */
	public static String getCurrentDateOrTime(String format) {
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();
		return convertDateIntoString(date, format);
	}

	public static String getTomorrowDate(String format) {
		return convertDateIntoString(getDateWrtCurrentDate(1), format);
	}

	public static String getYesterdayDate(String format) {
		return convertDateIntoString(getDateWrtCurrentDate(-1), format);
	}

	public static String getFutureDate(int differenceInPositive, String format) {
		return convertDateIntoString(getDateWrtCurrentDate(differenceInPositive), format);
	}

	private static Date getDateWrtCurrentDate(int difference) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, difference);
		return cal.getTime();
	}

	public static String getCurrentTimePlusMinutes(int minutesToAdd) {
		Calendar cal = Calendar.getInstance();
		long timeInSecs = cal.getTimeInMillis();
		Date afterAdding2Mins = new Date(timeInSecs + (minutesToAdd * 60 * 1000));
		return convertDateIntoString(afterAdding2Mins, "HH:mm");
	}
}
