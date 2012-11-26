/* PdfWriterFactory.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Oct 30, 2012 2:15:10 PM , Created by Sam
}}IS_NOTE

Copyright (C) 2012 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.zkoss.exporter.pdf;

import java.io.OutputStream;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfWriter;

/**
 * @author Sam
 *
 */
public interface PdfWriterFactory {

	public PdfWriter getPdfWriter(Document document, OutputStream os) throws Exception;
	
}
