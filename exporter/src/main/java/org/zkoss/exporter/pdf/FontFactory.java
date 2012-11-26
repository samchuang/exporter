/* FontFactory.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Oct 30, 2012 2:16:43 PM , Created by Sam
}}IS_NOTE

Copyright (C) 2012 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.zkoss.exporter.pdf;

import com.lowagie.text.Font;

/**
 * @author Sam
 *
 */
public interface FontFactory {

	public final static String FONT_TYPE_HEADER = "header";
	public final static String FONT_TYPE_CELL = "cell";
	public final static String FONT_TYPE_GROUP = "group";
	public final static String FONT_TYPE_GROUPFOOT = "groupfoot";
	public final static String FONT_TYPE_FOOTER = "footer";
	
	public Font getFont(String type);
}
