/* PdfPTableFactoryImpl.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Oct 30, 2012 11:42:12 AM , Created by Sam
}}IS_NOTE

Copyright (C) 2012 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.zkoss.exporter.pdf.impl;

import org.zkoss.exporter.pdf.PdfPTableFactory;

import com.lowagie.text.pdf.PdfPTable;

/**
 * @author Sam
 *
 */
public class PdfPTableFactoryImpl implements PdfPTableFactory {

	@Override
	public PdfPTable getPdfPTable(int columnSize) {
		PdfPTable table = new PdfPTable(columnSize);
		table.setHeaderRows(1);
		table.setWidthPercentage(100);
		return table;
	}
}
