/* FoodVM.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Nov 9, 2012 9:39:41 AM , Created by Sam
}}IS_NOTE

Copyright (C) 2012 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package demo.vm;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.exporter.GroupRenderer;
import org.zkoss.exporter.Interceptor;
import org.zkoss.exporter.excel.ExcelExporter;
import org.zkoss.exporter.excel.ExcelExporter.ExportContext;
import org.zkoss.exporter.pdf.FontFactory;
import org.zkoss.exporter.pdf.PdfExporter;
import org.zkoss.exporter.pdf.PdfPCellFactory;
import org.zkoss.exporter.util.GroupsModelArrayAdapter;
import org.zkoss.poi.ss.usermodel.Cell;
import org.zkoss.poi.ss.usermodel.CellStyle;
import org.zkoss.poi.ss.usermodel.Row;
import org.zkoss.poi.xssf.usermodel.XSSFCellStyle;
import org.zkoss.poi.xssf.usermodel.XSSFSheet;
import org.zkoss.poi.xssf.usermodel.XSSFWorkbook;
import org.zkoss.util.media.AMedia;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Grid;
import org.zkoss.zul.GroupsModelArray;
import org.zkoss.zul.Listbox;

import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

import demo.Food;
import demo.FoodComparator;
import demo.FoodData;

/**
 * @author Sam
 *
 */
public class FoodGroupVM {
	
	FoodGroupModelArray _model;
	
	@Init
	public void init() {
		_model = new FoodGroupModelArray(FoodData.getAllFoods().toArray(new Food[]{}), new FoodComparator());
	}

	public GroupsModelArray getFoodModel() {
		return _model;
	}
	
	@Command
	public void exportListbox(@BindingParam("ref") Listbox listbox) throws Exception {
		PdfExporter exporter = new PdfExporter();
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		exporter.export(listbox, out);
		
		AMedia amedia = new AMedia("FirstReport.pdf", "pdf", "application/pdf", out.toByteArray());
		Filedownload.save(amedia);
		
		out.close();
	}
	
	@Command
	public void exportByDataModel() throws Exception {
		final PdfExporter exporter = new PdfExporter();
		final PdfPCellFactory cellFactory = exporter.getPdfPCellFactory();
		final FontFactory fontFactory = exporter.getFontFactory();
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		final String[] headers = new String[]{"Name", "Top Nutrients", "% of Daily", "Calories", "Quantity"};
		exporter.setInterceptor(new Interceptor <PdfPTable> () {
			
			@Override
			public void beforeRendering(PdfPTable table) {
				for (int i = 0; i < headers.length; i++) {
					String header = headers[i];
					Font font = exporter.getFontFactory().getFont(FontFactory.FONT_TYPE_HEADER);
					
					PdfPCell cell = exporter.getPdfPCellFactory().getHeaderCell();
					cell.setPhrase(new Phrase(header, font));
					if ("% of Daily".equals(header) || "Calories".equals(header)) {
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					}
					table.addCell(cell);
				}
				table.completeRow();
			}
			
			@Override
			public void afterRendering(PdfPTable table) {
			}
		});
		exporter.export(headers.length, _model.getData(), new GroupRenderer<PdfPTable, Food>() {

			@Override
			public void render(PdfPTable table, Food food, boolean isOddRow) {
				Font font = fontFactory.getFont(FontFactory.FONT_TYPE_CELL);
				PdfPCell cell = cellFactory.getCell(isOddRow);
				
				cell.setPhrase(new Phrase(food.getName(), font));
				table.addCell(cell);
				
				cell = cellFactory.getCell(isOddRow);
				cell.setPhrase(new Phrase(food.getTopNutrients(), font));
				table.addCell(cell);
				
				cell = cellFactory.getCell(isOddRow);
				cell.setPhrase(new Phrase("" + food.getDailyPercent(), font));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				
				cell = cellFactory.getCell(isOddRow);
				cell.setPhrase(new Phrase("" + food.getCalories(), font));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				
				cell = cellFactory.getCell(isOddRow);
				cell.setPhrase(new Phrase(food.getQuantity(), font));
				table.addCell(cell);
				
				table.completeRow();
			}

			@Override
			public void renderGroup(PdfPTable table, Collection<Food> foods) {
				Iterator<Food> iterator = foods.iterator();
				if (iterator.hasNext()) {
					Food food = iterator.next();
					Font font = fontFactory.getFont(FontFactory.FONT_TYPE_GROUP);
					PdfPCell cell = cellFactory.getGroupCell();
					
					cell.setPhrase(new Phrase(food.getCategory(), font));
					cell.setColspan(headers.length);
					table.addCell(cell);
					
					table.completeRow();
				}
			}

			@Override
			public void renderGroupfoot(PdfPTable table, Collection<Food> foods) {
				Font font = fontFactory.getFont(FontFactory.FONT_TYPE_GROUPFOOT);
				PdfPCell cell = cellFactory.getGroupCell();
				cell.setPhrase(new Phrase("Total size: " + (foods != null ? foods.size() : 0), font));
				table.addCell(cell);
				
				cell = cellFactory.getCell(false);
				cell.setColspan(headers.length - 1);
				table.addCell(cell);
				table.completeRow();
			}
		}, out);
		
		AMedia amedia = new AMedia("FirstReport.pdf", "pdf", "application/pdf", out.toByteArray());
		Filedownload.save(amedia);
		
		out.close();
	}
	
	@Command
	public void exportGrid(@BindingParam("ref") Grid grid) throws Exception {
		PdfExporter exporter = new PdfExporter();
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		exporter.export(grid, out);
		
		AMedia amedia = new AMedia("FirstReport.pdf", "pdf", "application/pdf", out.toByteArray());
		Filedownload.save(amedia);
		
		out.close();
	}
	
	@Command
	public void exportListboxToExcel(@BindingParam("ref") Listbox listbox) throws Exception {
		ExcelExporter exporter = new ExcelExporter();
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		exporter.export(listbox, out);
		
		AMedia amedia = new AMedia("FirstReport.xlsx", "xls", "application/file", out.toByteArray());
		Filedownload.save(amedia);
		
		out.close();
		
	}
	
	@Command
	public void exportToExcelByDataModel() throws IOException {
		final ExcelExporter exporter = new ExcelExporter();

		final String[] headers = new String[]{"Name", "Top Nutrients", "% of Daily", "Calories", "Quantity"};
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		exporter
		.setInterceptor(new org.zkoss.exporter.Interceptor<XSSFWorkbook>() {
			
			@Override
			public void beforeRendering(XSSFWorkbook target) {
				ExportContext ctx = exporter.getExportContext();
				
				for (String header : headers) {
					Cell cell = exporter.getOrCreateCell(ctx.moveToNextCell(), ctx.getSheet());
					cell.setCellValue(header);
					
					if ("% of Daily".equals(header) || "Calories".equals(header)) {
						CellStyle srcStyle = cell.getCellStyle();
						if (srcStyle.getAlignment() != CellStyle.ALIGN_CENTER) {
							XSSFCellStyle newCellStyle = ctx.getSheet().getWorkbook().createCellStyle();
							newCellStyle.cloneStyleFrom(srcStyle);
							newCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
							cell.setCellStyle(newCellStyle);
						}
					}
				}
			}
			
			@Override
			public void afterRendering(XSSFWorkbook target) {
				
			}
		});
		exporter.export(5, _model.getData(), new org.zkoss.exporter.GroupRenderer<Row, Food>() {

			@Override
			public void render(Row row, Food food, boolean oddRow) {
				ExportContext ctx = exporter.getExportContext();
				XSSFSheet sheet = ctx.getSheet();
				
				exporter
				.getOrCreateCell(ctx.moveToNextCell(), sheet)
				.setCellValue(food.getName());
				
				exporter
				.getOrCreateCell(ctx.moveToNextCell(), sheet)
				.setCellValue(food.getTopNutrients());
				
				Cell cell = exporter.getOrCreateCell(ctx.moveToNextCell(), sheet);
				cell.setCellValue(food.getDailyPercent());
				CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
				cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
				cell.setCellStyle(cellStyle);
				
				cell = exporter.getOrCreateCell(ctx.moveToNextCell(), sheet);
				cell.setCellValue(food.getCalories());
				cellStyle = sheet.getWorkbook().createCellStyle();
				cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
				cell.setCellStyle(cellStyle);
				
				exporter
				.getOrCreateCell(ctx.moveToNextCell(), sheet)
				.setCellValue(food.getQuantity());
			}

			@Override
			public void renderGroup(Row row, Collection<Food> foods) {
				ExportContext ctx = exporter.getExportContext();
				XSSFSheet sheet = ctx.getSheet();
				
				exporter
				.getOrCreateCell(ctx.moveToNextCell(), sheet)
				.setCellValue(foods.iterator().next().getCategory());
			}

			@Override
			public void renderGroupfoot(Row row, Collection<Food> foods) {
				ExportContext ctx = exporter.getExportContext();
				XSSFSheet sheet = ctx.getSheet();
				
				exporter
				.getOrCreateCell(ctx.moveToNextCell(), sheet)
				.setCellValue("Total " + foods.size() + " items");
			}
			
		}, out);
		
		AMedia amedia = new AMedia("FirstReport.xlsx", "xls", "application/file", out.toByteArray());
		Filedownload.save(amedia);
		
		out.close();
	}
	
	@Command
	public void exportGridToExcel(@BindingParam("ref") Grid grid) throws Exception {
		ExcelExporter exporter = new ExcelExporter();
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		exporter.export(grid, out);
		
		AMedia amedia = new AMedia("FirstReport.xlsx", "xls", "application/file", out.toByteArray());
		Filedownload.save(amedia);
		
		out.close();
	}
	
	private class FoodGroupModelArray extends GroupsModelArrayAdapter<Food, Object, Object, Object> {

		private static final String footerString = "Total %d items";
		
		public FoodGroupModelArray(Food[] data, Comparator<Food> cmpr) {
			super(data, cmpr);
		}
		
		@Override
		protected Object createGroupHead(Food[] groupdata, int index, int col) {
	        String ret = "";
	        if (groupdata.length > 0) {
	            ret = ((Food)groupdata[0]).getCategory();
	        }
	        return ret;
	    }
		
		@Override
		protected Object createGroupFoot(Food[] groupdata, int index, int col) {
			return String.format(footerString, groupdata.length);
		}
	}
}
