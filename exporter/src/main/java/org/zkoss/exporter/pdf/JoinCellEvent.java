/* JoinCellEvent.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Oct 30, 2012 3:01:35 PM , Created by Sam
}}IS_NOTE

Copyright (C) 2012 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.zkoss.exporter.pdf;

import com.lowagie.text.pdf.PdfPCellEvent;

/**
 * @author Sam
 *
 */
public interface JoinCellEvent {
	
	public void addEvent(PdfPCellEvent event);
	
	public void removeEvent(PdfPCellEvent event);
	
}
