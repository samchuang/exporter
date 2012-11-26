/* PdfPTableFactory.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Oct 30, 2012 2:15:38 PM , Created by Sam
}}IS_NOTE

Copyright (C) 2012 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.zkoss.exporter.pdf;

import com.lowagie.text.pdf.PdfPTable;

/**
 * @author Sam
 *
 */
public interface PdfPTableFactory {

	public PdfPTable getPdfPTable(int columnSize);
	
}
