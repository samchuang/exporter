/* JoinCellEventImpl.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Oct 30, 2012 12:00:08 PM , Created by Sam
}}IS_NOTE

Copyright (C) 2012 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.zkoss.exporter.pdf.impl;

import java.util.Iterator;
import java.util.LinkedHashSet;

import org.zkoss.exporter.pdf.JoinCellEvent;

import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPCellEvent;

/**
 * @author Sam
 *
 */
public class JoinCellEventImpl implements JoinCellEvent, PdfPCellEvent {

	private LinkedHashSet<PdfPCellEvent> _events = new LinkedHashSet<PdfPCellEvent>();
	
	public JoinCellEventImpl(PdfPCellEvent... events) {
		for (PdfPCellEvent event : events) {
			_events.add(event);
		}
	}
	
	@Override
	public void addEvent(PdfPCellEvent event) {
		_events.add(event);
	}
	
	@Override
	public void removeEvent(PdfPCellEvent event) {
		_events.remove(event);
	}

	@Override
	public void cellLayout(PdfPCell cell, Rectangle position, PdfContentByte[] canvases) {
		Iterator<PdfPCellEvent> iterator = _events.iterator();
		while (iterator.hasNext()) {
			iterator.next().cellLayout(cell, position, canvases);
		}
	}
	
}
