package com.prudential.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.prudential.exception.DateFormatNotValidException;

public class Utilities {

	private Utilities() {
	}

	public static void main(String[] args){
		String s = "2022-12-30";
		String e = "2023-01-10";
		LocalDate start = LocalDate.parse(s);
		LocalDate end = LocalDate.parse(e);
		List<LocalDate> dateList = getListDateByRange(start, end);
		LinkedHashMap<LocalDate, Boolean> dateMap = new LinkedHashMap <LocalDate,Boolean>();
		for(LocalDate date : dateList){
			dateMap.put(date, true);
		}

		LocalDate r1 = LocalDate.parse("2023-01-01");
		LocalDate r2 = LocalDate.parse("2023-01-03");
		for(LocalDate date : getListDateByRange(r1, r2)){
			dateMap.put(date, false);
		}
		System.out.println(dateList);
	}
	/**
	 * Generate data list for [start,end]
	 * @param start
	 * @param end
	 * @return
	 */
	public static List<LocalDate> getListDateByRange(LocalDate start, LocalDate end){
		List<LocalDate> dateList = new ArrayList<>();
		while (!start.isAfter(end)) {
			dateList.add(start);
			start = start.plusDays(1);
		}
		return dateList;
	}
	/**
	 * Converts DTO string to date format yyyy-mm-dd
	 * 
	 * @throws DateFormatNotValidException
	 * 
	 */
	public static LocalDate toLocalDate(String date) throws DateFormatNotValidException {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		try {
			return LocalDate.parse(date, formatter);
		} catch (DateTimeParseException e) {
			throw new DateFormatNotValidException("Provided date formt is not valid use yyyy-MM-dd");
		}
	}

	/**
	 * Check if date from <= date to
	 * 
	 */
	public static boolean isValidDates(LocalDate dateFrom, LocalDate dateTo) {
		return dateFrom.isBefore(dateTo) || dateFrom.isEqual(dateTo) ;
	}

	/**
	 * Check if dateTime in [rentFrom,rentTo]
	 * @param dateTime
	 * @param rentFrom
	 * @param rentTo
	 * @return
	 */
	public static boolean isDateTimeWithinPeriod(LocalDate dateTime, LocalDate rentFrom, LocalDate rentTo) {
		return dateTime.compareTo(rentFrom) >= 0 && dateTime.compareTo(rentTo) <= 0;
	}


}
