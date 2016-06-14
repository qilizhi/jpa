package com.qlz.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.format.Formatter;

public class DateFormatter implements Formatter<Date> {

	public String print(Date object, Locale locale) {
		return object.toString()+locale.toString();
	}

	public Date parse(String text, Locale locale) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = format.parse(text);
		} catch (Exception e) {
			try {
				format = new SimpleDateFormat("yyyy-MM-dd");
				date = format.parse(text);
			} catch (Exception e1) {
				format = new SimpleDateFormat("yyyyMMdd");
				date = format.parse(text);
			}
		}
		return date;
	}

}