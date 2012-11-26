/* DateParserTest.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Nov 14, 2012 12:30:14 PM , Created by Sam
}}IS_NOTE

Copyright (C) 2012 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.zkoss.exporter.excel.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.zkoss.exporter.excel.imp.DateParser;
import org.zkoss.exporter.excel.imp.ParseDateException;

/**
 * @author Sam
 *
 */
public abstract class AbstractDateParserTest {

	public abstract Locale getLocale();
	
	public DateParser getDateParser() {
		return new DateParser(getLocale());
	}
	
	protected DateParser dateParser;
	
	@Before
	public void setup() {
		dateParser = getDateParser();
	}
	
	@Test
	public void testShortFormat() throws ParseDateException {
		DateFormat shortFormat = DateFormat.getDateInstance(DateFormat.SHORT, getLocale()); 
		String val = shortFormat.format(new Date());
		
		Date date = dateParser.parseToDate(val);
		
		Assert.assertNotNull(date);
		Assert.assertEquals(val, shortFormat.format(date));
	}
	
	@Test
	public void testMediumFormat() throws ParseDateException {
		DateFormat shortFormat = DateFormat.getDateInstance(DateFormat.MEDIUM, getLocale());
		String val = shortFormat.format(new Date());
		
		Date date = dateParser.parseToDate(val);
		
		Assert.assertNotNull(date);
		Assert.assertEquals(val, shortFormat.format(date));
	}
	
	@Test
	public void testFullFormat() throws ParseDateException {
		DateFormat shortFormat = DateFormat.getDateInstance(DateFormat.LONG, getLocale());
		String val = shortFormat.format(new Date());
		
		Date date = dateParser.parseToDate(val);
		
		Assert.assertNotNull(date);
		Assert.assertEquals(val, shortFormat.format(date));
	}
	
//	@Test
//	public void testFormat_ddMMMYY() throws ParseDateException {
//		DateFormat dateFormater = new SimpleDateFormat("dd MMM yy");
//		String val = dateFormater.format(new Date());
//		
//		Date date = dateParser.parseToDate(val);
//		
//		Assert.assertNotNull(date);
//		Assert.assertEquals(val, dateFormater.format(date));
//	}
//	
//	@Test
//	public void testFormat_MMMdd() throws ParseDateException {
//		DateFormat dateFormater = new SimpleDateFormat("MMM dd");
//		String val = dateFormater.format(new Date());
//		
//		Date date = dateParser.parseToDate(val);
//		
//		System.out.println(val + " : " + date);
//		
//		Assert.assertNotNull(date);
//		Assert.assertEquals(val, dateFormater.format(date));
//	}
//	
//	@Test
//	public void testFormat_ddMMM() throws ParseDateException {
//		DateFormat dateFormater = new SimpleDateFormat("dd MMM");
//		String val = dateFormater.format(new Date());
//		
//		Date date = dateParser.parseToDate(val);
//		
//		System.out.println(val + " : " + date);
//		
//		Assert.assertNotNull(date);
//		Assert.assertEquals(val, dateFormater.format(date));
//	}
}
