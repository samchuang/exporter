/* FontFactoryImpl.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Oct 30, 2012 11:51:02 AM , Created by Sam
}}IS_NOTE

Copyright (C) 2012 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.zkoss.exporter.pdf.impl;

import java.awt.Color;

import org.zkoss.exporter.pdf.FontFactory;

import com.lowagie.text.Font;
import com.lowagie.text.html.WebColors;

/**
 * @author Sam
 *
 */
public class FontFactoryImpl implements FontFactory {
	
	protected float defaultFontSize = 10;
	protected Color defaultFontColor = WebColors.getRGBColor("#636363");
	
	protected Font getHeaderFont() {
		Font font = getDefaultFont();
		font.setStyle(Font.BOLD);
		return font;
	}

	protected Font getCellFont() {
		return getDefaultFont();
	}

	protected Font getGroupFont() {
		Font font = getDefaultFont();
		font.setStyle(Font.BOLD);
		return font;
	}

	protected Font getGroupfootFont() {
		Font font = getDefaultFont();
		font.setStyle(Font.BOLD);
		return font;
	}

	protected Font getFooterFont() {
		return getDefaultFont();
	}
	
	protected Font getDefaultFont() {
		Font font = new Font();
		font.setColor(defaultFontColor);
		font.setSize(defaultFontSize);
		return font;
	}

	public Font getFont(String type) {
		if (FontFactory.FONT_TYPE_HEADER.equals(type)) {
			return getHeaderFont();
		} else if (FontFactory.FONT_TYPE_CELL.equals(type)) {
			return getCellFont();
		} else if (FontFactory.FONT_TYPE_GROUP.equals(type)) {
			return getGroupFont();
		} else if (FontFactory.FONT_TYPE_GROUPFOOT.equals(type)) {
			return getGroupfootFont();
		} else if (FontFactory.FONT_TYPE_FOOTER.equals(type)) {
			return getFooterFont();
		}
		return getDefaultFont();
	}
}
