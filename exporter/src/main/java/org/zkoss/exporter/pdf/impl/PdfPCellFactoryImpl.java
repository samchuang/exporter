/* PdfPCellFactoryImpl.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Oct 30, 2012 11:57:27 AM , Created by Sam
}}IS_NOTE

Copyright (C) 2012 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.zkoss.exporter.pdf.impl;

import java.awt.Color;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.zkoss.exporter.pdf.JoinCellEvent;
import org.zkoss.exporter.pdf.PdfExporter;
import org.zkoss.exporter.pdf.PdfPCellFactory;

import com.lowagie.text.BadElementException;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.html.WebColors;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPCellEvent;
import com.lowagie.text.pdf.PdfPTable;

/**
 * @author Sam
 *
 */
public class PdfPCellFactoryImpl implements PdfPCellFactory {
	
	protected Color defaultBorderColor = WebColors.getRGBColor("#CFCFCF");
	protected Color defaultOddRowBackgroundColor = WebColors.getRGBColor("#F7F7F7");
	protected Color defaultFooterBackgroundColor = WebColors.getRGBColor("#F9F9F9");
	
	protected JoinCellEvent headerEventJoiner = new JoinCellEventImpl(new PdfPCellEvent() {

		@Override
		public void cellLayout(PdfPCell cell, Rectangle position, PdfContentByte[] canvases) {
			PdfContentByte pdfContentByte = canvases[PdfPTable.BACKGROUNDCANVAS];
			URL resource = PdfExporter.class.getResource("column-bg.png");
			Image image;
			try {
				image = Image.getInstance(resource.toString());
				pdfContentByte.addImage(image, cell.getWidth(), 0, 0, position.getHeight(), position.getLeft(), position.getBottom());
			} catch (BadElementException e) {
				throw new RuntimeException(e);
			} catch (MalformedURLException e) {
				throw new RuntimeException(e);
			} catch (IOException e) {
				throw new RuntimeException(e);
			} catch (DocumentException e) {
				throw new RuntimeException(e);
			}
		}
	});
	protected JoinCellEvent groupEventJoiner = new JoinCellEventImpl(new PdfPCellEvent() {

		@Override
		public void cellLayout(PdfPCell cell, Rectangle position, PdfContentByte[] canvases) {
			PdfContentByte pdfContentByte = canvases[PdfPTable.BACKGROUNDCANVAS];
			Image image;
			try {
				URL resource = PdfExporter.class.getResource("group_bg.gif");
				image = Image.getInstance(resource.toString());
				pdfContentByte.addImage(image, cell.getWidth(), 0, 0, position.getHeight(), position.getLeft(), position.getBottom());
			} catch (BadElementException e) {
				throw new RuntimeException(e);
			} catch (MalformedURLException e) {
				throw new RuntimeException(e);
			} catch (IOException e) {
				throw new RuntimeException(e);
			} catch (DocumentException e) {
				throw new RuntimeException(e);
			}
		}
	});
	
	protected JoinCellEvent groupfootEventJoiner = new JoinCellEventImpl(new PdfPCellEvent() {

		@Override
		public void cellLayout(PdfPCell cell, Rectangle position, PdfContentByte[] canvases) {
			PdfContentByte pdfContentByte = canvases[PdfPTable.BACKGROUNDCANVAS];
			Image image;
			try {
				URL resource = PdfExporter.class.getResource("groupfoot_bg.gif");
				image = Image.getInstance(resource.toString());
				pdfContentByte.addImage(image, cell.getWidth(), 0, 0, position.getHeight(), position.getLeft(), position.getBottom());
			} catch (BadElementException e) {
				throw new RuntimeException(e);
			} catch (MalformedURLException e) {
				throw new RuntimeException(e);
			} catch (IOException e) {
				throw new RuntimeException(e);
			} catch (DocumentException e) {
				throw new RuntimeException(e);
			}
		}
	});
	
	@Override
	public PdfPCell getHeaderCell() {
		PdfPCell cell = new PdfPCell();
		cell.setPaddingTop(8);
		cell.setPaddingRight(4);
		cell.setPaddingBottom(7);
		cell.setPaddingLeft(6);
		
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		
		cell.setBorderColor(defaultBorderColor);
		cell.setCellEvent((PdfPCellEvent)headerEventJoiner);
		return cell;
	}

	@Override
	public PdfPCell getCell(boolean isOddRow) {
		PdfPCell cell = getDefaultPdfPCell();
		if (isOddRow)
			cell.setBackgroundColor(defaultOddRowBackgroundColor);
		return cell;
	}

	@Override
	public PdfPCell getGroupCell() {
		PdfPCell cell = getDefaultPdfPCell();
		cell.setCellEvent((PdfPCellEvent)groupEventJoiner);
		return cell;
	}

	@Override
	public PdfPCell getGroupfootCell() {
		PdfPCell cell = new PdfPCell();
		cell.setBorderColor(defaultBorderColor);
		cell.setPaddingTop(5);
		cell.setPaddingRight(4);
		cell.setPaddingBottom(5);
		cell.setPaddingLeft(6);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		
		cell.setCellEvent((PdfPCellEvent)groupfootEventJoiner);
		return cell;
	}

	@Override
	public PdfPCell getFooterCell() {
		PdfPCell cell = new PdfPCell();
		cell.setBorderColor(defaultBorderColor);
		cell.setPaddingTop(5);
		cell.setPaddingRight(10);
		cell.setPaddingBottom(5);
		cell.setPaddingLeft(9);
		cell.setBackgroundColor(defaultFooterBackgroundColor);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		return cell;
	}
	
	protected PdfPCell getDefaultPdfPCell() {
		PdfPCell cell = new PdfPCell();
		cell.setBorderColor(defaultBorderColor);
		cell.setPaddingTop(4);
		cell.setPaddingRight(4);
		cell.setPaddingBottom(4);
		cell.setPaddingLeft(6);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		return cell;
	}
}