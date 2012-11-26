/* ExcelExporter.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Nov 12, 2012 10:58:26 AM , Created by Sam
}}IS_NOTE

Copyright (C) 2012 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.zkoss.exporter.excel;

import static org.zkoss.exporter.util.Utils.getAlign;
import static org.zkoss.exporter.util.Utils.getFooters;
import static org.zkoss.exporter.util.Utils.getHeaderSize;
import static org.zkoss.exporter.util.Utils.getHeaders;
import static org.zkoss.exporter.util.Utils.getStringValue;
import static org.zkoss.exporter.util.Utils.getTarget;
import static org.zkoss.exporter.util.Utils.invokeComponentGetter;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.CellStyle;
import org.zkoss.exporter.AbstractExporter;
import org.zkoss.exporter.GroupRenderer;
import org.zkoss.exporter.RowRenderer;
import org.zkoss.exporter.excel.imp.CellValueSetterFactoryImpl;
import org.zkoss.poi.ss.usermodel.Cell;
import org.zkoss.poi.ss.usermodel.Row;
import org.zkoss.poi.ss.usermodel.Sheet;
import org.zkoss.poi.xssf.usermodel.XSSFCellStyle;
import org.zkoss.poi.xssf.usermodel.XSSFSheet;
import org.zkoss.poi.xssf.usermodel.XSSFWorkbook;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Auxhead;
import org.zkoss.zul.impl.HeaderElement;
import org.zkoss.zul.impl.MeshElement;

/**
 * @author Sam
 *
 */
public class ExcelExporter extends AbstractExporter <XSSFWorkbook, Row> {

	private ExportContext _exportContext;
	private CellValueSetterFactory _cellValueSetterFactory;
	
	public <D> void export(int columnSize, Collection<D> data, RowRenderer<Row, D> renderer, OutputStream outputStream) throws IOException {
		XSSFWorkbook book = new XSSFWorkbook();
		ExportContext ctx = new ExportContext(true, book.createSheet("Sheet1"));
		XSSFSheet sheet = ctx.getSheet();
		setExportContext(ctx);
		
		if (getInterceptor() != null)
			getInterceptor().beforeRendering(book);
		int rowIndex = 0;
		for (D d : data) {
			renderer.render(getOrCreateRow(ctx.moveToNextRow(), sheet), d, (rowIndex++ % 2 == 1));
		}
		if (getInterceptor() != null)
			getInterceptor().afterRendering(book);
		
		adjustColumnWidth(columnSize);
		
		book.write(outputStream);
		setExportContext(null);
	}
	
	public <D> void export(int columnSize, Collection< Collection <D> > data, GroupRenderer<Row, D> renderer, OutputStream outputStream) throws IOException {
		XSSFWorkbook book = new XSSFWorkbook();
		ExportContext ctx = new ExportContext(true, book.createSheet("Sheet1"));
		XSSFSheet sheet = ctx.getSheet();
		setExportContext(ctx);
		
		if (getInterceptor() != null)
			getInterceptor().beforeRendering(book);
		
		int rowIndex = 0;
		for (Collection <D> group : data) {
			renderer.renderGroup(getOrCreateRow(ctx.moveToNextRow(), sheet), group);
			for (D d : group) {
				renderer.render(getOrCreateRow(ctx.moveToNextRow(), sheet), d, (rowIndex++ % 2 == 1));
			}
			renderer.renderGroupfoot(getOrCreateRow(ctx.moveToNextRow(), sheet), group);
		}
		
		if (getInterceptor() != null)
			getInterceptor().afterRendering(book);
		
		adjustColumnWidth(columnSize);
		book.write(outputStream);
		setExportContext(null);
	}
	
	public void setExportContext(ExportContext ctx) {
		_exportContext = ctx;
	}
	
	public ExportContext getExportContext() {
		return _exportContext;
	}
	
	public void setCellValueSetterFactory(CellValueSetterFactory cellValueSetterFactory) {
		_cellValueSetterFactory = cellValueSetterFactory;
	}
	
	public CellValueSetterFactory getCellValueSetterFactory() {
		if (_cellValueSetterFactory == null) {
			_cellValueSetterFactory = new CellValueSetterFactoryImpl();
		}
		return _cellValueSetterFactory;
	}
	
	private void adjustColumnWidth(int columnSize) {
		XSSFSheet sheet = getExportContext().getSheet();
		for (int c = 0; c < columnSize; c++) {
			sheet.autoSizeColumn(c);
		}
	}
	
	@Override
	protected void exportTabularComponent(MeshElement component, OutputStream outputStream) throws Exception {
		XSSFWorkbook book = new XSSFWorkbook();
		setExportContext(new ExportContext(true, book.createSheet("Sheet1")));
		
		int columnSize = getHeaderSize(component);
		exportHeaders(columnSize, component, book);
		exportRows(columnSize, component, book);
		exportFooters(columnSize, component, book);
		
		adjustColumnWidth(columnSize);
		
		book.write(outputStream);
		setExportContext(null);
	}

	@Override
	protected void exportAuxhead(int columnSize, Auxhead auxhead, XSSFWorkbook book) {
		//TODO: process row span
		exportCellsWithSpan(columnSize, auxhead, book);
	}
	
	private void setCellAlignment(short alignment, Cell cell, XSSFWorkbook book) {
		if (cell.getCellStyle().getAlignment() != alignment) {
			XSSFCellStyle cellStyle = book.createCellStyle();
			cellStyle.cloneStyleFrom(cell.getCellStyle());
			cellStyle.setAlignment(alignment);
			cell.setCellStyle(cellStyle);
		}
	}

	private boolean syncAlignment(Component cmp, Cell cell, XSSFWorkbook book) {
		if (cmp == null)
			return false;
		
		final String align = getAlign(cmp);
		if ("center".equals(align)) {
			setCellAlignment(CellStyle.ALIGN_CENTER, cell, book);
			return true;
		} else if ("right".equals(align)) {
			setCellAlignment(CellStyle.ALIGN_RIGHT, cell, book);
			return true;
		} else if ("left".equals(align)) {
			setCellAlignment(CellStyle.ALIGN_LEFT, cell, book);
			return true;
		}
		return false;
	}
	
	
	private void syncAlignment(Component cmp, Component header, Cell cell, XSSFWorkbook book) {
		//check if component define align, if not check header's align
		if (!syncAlignment(cmp, cell, book) && header != null) {
			syncAlignment(header, cell, book);
		}
	}
	
	@Override
	protected void exportColumnHeaders(Component component, XSSFWorkbook book) {
		CellValueSetter<Component> cellValueSetter = getCellValueSetterFactory().getCellValueSetter(Component.class);
		ExportContext ctx = getExportContext();
		XSSFSheet sheet = ctx.getSheet();
		for (Component c : component.getChildren()) {
			
			Cell cell = getOrCreateCell(ctx.moveToNextCell(), sheet);
			cellValueSetter.setCellValue(c, cell);
			syncAlignment(c, cell, book);
		}
		ctx.moveToNextRow();
	}

	@Override
	protected void exportGroup(int columnSize, Component group, XSSFWorkbook book) {
		Iterator<Component> iterator = group.getChildren().iterator();
		
		CellValueSetter<Component> cellValueSetter = getCellValueSetterFactory().getCellValueSetter(Component.class);
		ExportContext context = getExportContext();
		XSSFSheet sheet = context.getSheet();
		while (iterator.hasNext()) {
			Component cmp = iterator.next();
			
			Cell cell = getOrCreateCell(context.moveToNextCell(), sheet);
			cellValueSetter.setCellValue(cmp, cell);
		}
		context.moveToNextRow();
	}

	@Override
	protected void exportGroupfoot(int columnSize, Component groupfoot,	XSSFWorkbook book) {
		exportCellsWithSpan(columnSize, groupfoot, book);
	}

	@Override
	protected void exportCells(int rowIndex, int columnSize, Component row,	XSSFWorkbook book) {
		CellValueSetter<Component> cellValueSetter = getCellValueSetterFactory().getCellValueSetter(Component.class);
		ExportContext ctx = getExportContext();
		XSSFSheet sheet = ctx.getSheet();
		
		HashMap<Integer, Component> headers = buildHeaderIndexMap(getHeaders(getTarget(row)));
		List<Component> children = row.getChildren();
		for (int c =0; c < columnSize; c++) {
			Component cmp = c < children.size() ? children.get(c) : null;
			
			if (cmp == null) {
				return;
			}
			
			Cell cell = getOrCreateCell(ctx.moveToNextCell(), sheet);
			cellValueSetter.setCellValue(cmp, cell);
			
			syncAlignment(cmp, headers != null ? headers.get(c) : null, cell, book);
		}
		ctx.moveToNextRow();
	}
	
	@Override
	protected void exportFooters(int columnSize, Component target, XSSFWorkbook book) {
		Component footers = getFooters(target);
		if (footers == null) {
			return;
		}
		exportCellsWithSpan(columnSize, footers, book);
	}
	
	private void exportCellsWithSpan(int columnSize, Component component, XSSFWorkbook book) {
		ExportContext ctx = getExportContext();
		XSSFSheet sheet = ctx.getSheet();
		for (Component cmp : component.getChildren()) {
			int span = getColSpan(cmp);
			if (span == 1) {
				getOrCreateCell(ctx.moveToNextCell(), sheet).setCellValue(getStringValue(cmp));
			} else {
				//TODO: merge col span
				//TODO: not tested yet
				int colIdx = ctx.getColumnIndex() + span;
				ctx.setColumnIndex(colIdx);
				getOrCreateCell(ctx.getRowIndex(), colIdx, sheet).setCellValue(getStringValue(cmp));
			}
		}
		ctx.moveToNextRow();
	}
	
	private HashMap<Integer, Component> buildHeaderIndexMap(Component target) {
		if (target == null)
			return null;
		
		HashMap<Integer, Component> headers = new HashMap<Integer, Component>();
		
		int idx = 0;
		for (Component c : target.getChildren()) {
			if (!(c instanceof HeaderElement)) {
				throw new IllegalArgumentException(c + " is not type of HeaderElement");
			}
			headers.put(idx++, c);
		}
		
		return headers;
	}
	
	public static Row getOrCreateRow(int[] idx, Sheet sheet) {
		return getOrCreateRow(idx[0], sheet);
	}
	
	public static Row getOrCreateRow(int row, Sheet sheet) {
		Row r = sheet.getRow(row);
		if (r == null) {
			return sheet.createRow(row);
		}
		return r;
	}
	
	private static int getColSpan(Component cmp) {
		int span = 1;
		Object spanVal = invokeComponentGetter(cmp, "getColspan", "getSpan");
		if (spanVal != null && spanVal instanceof Number)
			span = ((Number)spanVal).intValue();
		return span;
	}
	
	public static Cell getOrCreateCell(int[] idx, Sheet sheet) {
		return getOrCreateCell(idx[0], idx[1], sheet);
	}
	
	public static Cell getOrCreateCell(int row, int col, Sheet sheet) {
		Row r = getOrCreateRow(row, sheet);
		Cell cell = r.getCell(col);
		if (cell == null) {
			return r.createCell(col);
		}
		return cell;
	}
	
	public static class ExportContext {
		int _rowIndex = -1;
		int _columnIndex = -1;
		final XSSFSheet _sheet;
		final boolean _exportByComponentReference;
		
		ExportContext (boolean isExportByComponentReference, XSSFSheet worksheet) {
			_exportByComponentReference = isExportByComponentReference;
			_sheet = worksheet;
		}
		
		public boolean isExportByComponentReference() {
			return _exportByComponentReference;
		}
		
		public void setRowIndex(int rowIndex) {
			_rowIndex = rowIndex;
		}
		
		public int getRowIndex() {
			return _rowIndex;
		}
		
		public void setColumnIndex(int columnIndex) {
			_columnIndex = columnIndex;
		}
		
		public int getColumnIndex() {
			return _columnIndex;
		}
		
		public int[] moveToNextCell() {
			return new int[]{_rowIndex < 0 ? _rowIndex = 0 : _rowIndex, _columnIndex < 0 ? _columnIndex = 0 : ++_columnIndex};
		}
		
		public int[] moveToNextRow() {
			return new int[]{++_rowIndex, _columnIndex = -1};
		}
		
		public XSSFSheet getSheet() {
			return _sheet;
		}
	}

	//TODO: not tested yet
	@Override
	public <D> void export(String[] columnHeaders, Collection<D> data,
			RowRenderer<Row, D> renderer, OutputStream outputStream) throws Exception {
		final int columnSize = columnHeaders.length;
		
		//TODO: need to log if not ExportColumnHeaderInterceptorImpl ?
		if (getInterceptor() == null)
			setInterceptor(new ExportColumnHeaderInterceptorImpl(columnHeaders));
		export(columnSize, data, renderer, outputStream);
	}
	
	//TODO: not tested yet
	@Override
	public <D> void export(String[] columnHeaders, Collection<Collection<D>> data,
			GroupRenderer<Row, D> renderer,	OutputStream outputStream) throws Exception {
		
		if (getInterceptor() == null)
			setInterceptor(new ExportColumnHeaderInterceptorImpl(columnHeaders));
		
		export(columnHeaders, data, renderer, outputStream);
	}
	
	//export header
	private class ExportColumnHeaderInterceptorImpl implements org.zkoss.exporter.Interceptor <XSSFWorkbook> {

		private final String[] _columnHeaders;
		public ExportColumnHeaderInterceptorImpl(String[] columnHeaders) {
			_columnHeaders = columnHeaders;
		}
		
		@Override
		public void beforeRendering(XSSFWorkbook book) {
			int columnSize = _columnHeaders.length;
			boolean renderHeader = false;
			for (int i = 0; i < columnSize; i++) {
				String e = _columnHeaders[i];
				if (e != null && e.length() > 0) {
					renderHeader = true;
					break;
				}
			}
			if (renderHeader) {
				ExportContext ctx = getExportContext();
				XSSFSheet sheet = ctx.getSheet();
				
				for (String header : _columnHeaders) {
					getOrCreateCell(ctx.moveToNextCell(), sheet).setCellValue(header);
				}
			}
		}

		@Override
		public void afterRendering(XSSFWorkbook book) {
		}
	}
}