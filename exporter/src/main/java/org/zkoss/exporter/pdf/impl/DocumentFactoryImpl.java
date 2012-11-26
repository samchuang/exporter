/* DocumentFactoryImpl.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Oct 30, 2012 11:33:28 AM , Created by Sam
}}IS_NOTE

Copyright (C) 2012 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.zkoss.exporter.pdf.impl;

import org.zkoss.exporter.pdf.DocumentFactory;

import com.lowagie.text.Document;

/**
 * @author Sam
 *
 */
public class DocumentFactoryImpl implements DocumentFactory {

	@Override
	public Document getDocument() {
		return new Document();
	}
}