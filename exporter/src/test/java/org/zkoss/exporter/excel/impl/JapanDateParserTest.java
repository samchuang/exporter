/* JpDateParserTest.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Nov 14, 2012 12:36:26 PM , Created by Sam
}}IS_NOTE

Copyright (C) 2012 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.zkoss.exporter.excel.impl;

import java.util.Locale;

/**
 * @author Sam
 *
 */
public class JapanDateParserTest extends AbstractDateParserTest {

	@Override
	public Locale getLocale() {
		return Locale.JAPAN;
	}
}
