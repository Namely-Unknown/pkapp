package com.plantkeeper.export;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.plantkeeper.entity.Product;
import com.plantkeeper.entity.Shipment;
import com.plantkeeper.entity.ShipmentItem;

public class AvailabilityReportExcelExporter extends ExcelExporter {

	private XSSFWorkbook workbook;
	private XSSFSheet productSheet;
	private XSSFSheet itemSheet;
	private List<Product> products;

	public AvailabilityReportExcelExporter(List<Product> productList) {
		this.products = productList;
		workbook = new XSSFWorkbook();
	}

	@Override
	public void writeHeaderLine() {
		productSheet = workbook.createSheet("Available Products");

		Row row = productSheet.createRow(0);

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setFontHeight(12);
		font.setColor(IndexedColors.WHITE.getIndex());
		// style.setBorderBottom(BorderStyle.MEDIUM);
		style.setFillForegroundColor(IndexedColors.BLUE_GREY.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setFont(font);

		int colNumber = 0;

		CellMaker.createCell(productSheet, row, colNumber++, "Product SKU", style);
		CellMaker.createCell(productSheet, row, colNumber++, "Plant Name", style);
		CellMaker.createCell(productSheet, row, colNumber++, "Container", style);
		CellMaker.createCell(productSheet, row, colNumber++, "Price", style);
		CellMaker.createCell(productSheet, row, colNumber++, "Avail Units", style);
	}

	@Override
	public void writeDataLines() {
		int itemRowCount = 1;
		int orderRowCount = 1;
		// Prep my cell styles
		CellStyle style = workbook.createCellStyle();
		CellStyle currencyStyle = workbook.createCellStyle();
		CellStyle dateStyle = workbook.createCellStyle();

		// Set up cell styles
		XSSFDataFormat df = workbook.createDataFormat();
		currencyStyle.setDataFormat(df.getFormat("$#,##0.00;$ (#,##0.00)"));
		dateStyle.setDataFormat(df.getFormat("m/d/yy"));

		// Write the Order Data
		for (Product product : products) {
				int orderColCount = 0;
				Row orderRow = productSheet.createRow(orderRowCount++);
				CellMaker.createCell(productSheet, orderRow, orderColCount++, product.getPlant().getCategory().getSkuPrefix() + product.getSkuInt(), style);
				CellMaker.createCell(productSheet, orderRow, orderColCount++, product.getPlant().getName(), style);
				CellMaker.createCell(productSheet, orderRow, orderColCount++, product.getContainer().getName(), style);
				CellMaker.createCell(productSheet, orderRow, orderColCount++, product.getPrice(), currencyStyle);
				CellMaker.createCell(productSheet, orderRow, orderColCount++, product.getUnitsInStock(), style);
		}

	}

	public void export(HttpServletResponse response) throws IOException {
		writeHeaderLine();
		writeDataLines();

		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();

		outputStream.close();

	}

}
