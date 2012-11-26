/* PdfWriterFactoryImpl.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Oct 30, 2012 11:36:53 AM , Created by Sam
}}IS_NOTE

Copyright (C) 2012 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.zkoss.exporter.pdf.impl;

import java.io.OutputStream;

import org.zkoss.exporter.pdf.PdfWriterFactory;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfWriter;

/**
 * @author Sam
 *
 */
public class PdfWriterFactoryImpl implements PdfWriterFactory {

	@Override
	public PdfWriter getPdfWriter(Document document, OutputStream os) throws Exception {
		return PdfWriter.getInstance(document, os);
	}
}
