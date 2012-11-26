/* PdfPCellFactory.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Oct 30, 2012 2:41:42 PM , Created by Sam
}}IS_NOTE

Copyright (C) 2012 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.zkoss.exporter.pdf;

import com.lowagie.text.pdf.PdfPCell;

/**
 * @author Sam
 *
 */
public interface PdfPCellFactory {

	public PdfPCell getHeaderCell();
	
	public PdfPCell getCell(boolean isOddRow);
	
	public PdfPCell getGroupCell();
	
	public PdfPCell getGroupfootCell();
	
	public PdfPCell getFooterCell();
}
