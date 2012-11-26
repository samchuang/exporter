/* FullMonthData.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Nov 14, 2012 12:11:32 PM , Created by Sam
}}IS_NOTE

Copyright (C) 2012 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.zkoss.exporter.excel.imp;

import java.text.DateFormatSymbols;
import java.util.Locale;

import org.zkoss.util.CacheMap;
import org.zkoss.util.Pair;

/**
 * @author Sam
 *
 */
public class FullMonthData extends CircularData {
	private FullMonthData(String[] data, int type, Locale locale) {
		super(data, type, locale);
	}
	private static final CacheMap _monthData;
	static {
		_monthData = new CacheMap(4);
		_monthData.setLifetime(24*60*60*1000);
	}
	public static FullMonthData getInstance(int type, Locale locale) {
		final Pair key = new Pair(locale, Integer.valueOf(type));
		FullMonthData value = (FullMonthData) _monthData.get(key);
		if (value == null) { //create and cache
			DateFormatSymbols symbols = DateFormatSymbols.getInstance(locale);
			if (symbols == null) {
				symbols = DateFormatSymbols.getInstance(Locale.US);
			}
			String[] month13 = symbols.getMonths();
			String[] month12 = new String[12];
			System.arraycopy(month13, 0, month12, 0, 12);
			value = new FullMonthData(month12, type, locale);
			_monthData.put(key, value);
		}
		return value;
	}
}
