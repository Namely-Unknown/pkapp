package com.plantkeeper.export;

import java.io.IOException;
import java.math.BigDecimal;
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

import com.plantkeeper.entity.Company;
import com.plantkeeper.entity.CustomerOrder;
import com.plantkeeper.entity.OrderItem;

// NOTATED
/**
 * A class to generate Excel documents to show Sales by Client
 * @author bobde
 *
 */
public class SalesByClientReport extends ExcelExporter {

	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private List<Company> customers;

	public SalesByClientReport(List<Company> customerList) {
		this.customers = customerList;
		workbook = new XSSFWorkbook();
	}

	public void writeHeaderLine() {

		// Create the Sheet for the orders
		sheet = workbook.createSheet("Sales Data");

		Row row = sheet.createRow(0);

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setFontHeight(12);
		font.setColor(IndexedColors.WHITE.getIndex());
		// style.setBorderBottom(BorderStyle.MEDIUM);
		style.setFillForegroundColor(IndexedColors.BLUE_GREY.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setFont(font);

		int colNumber = 0;

		CellMaker.createCell(sheet, row, colNumber++, "Client ID", style);
		CellMaker.createCell(sheet, row, colNumber++, "Client Name", style);
		CellMaker.createCell(sheet, row, colNumber++, "Units Sold", style);
		CellMaker.createCell(sheet, row, colNumber++, "Billed", style);
		CellMaker.createCell(sheet, row, colNumber++, "Received", style);
		CellMaker.createCell(sheet, row, colNumber++, "Invoices", style);

	}

	public void writeDataLines() {
		int rowCount = 1;
		// Prep my cell styles
		CellStyle style = workbook.createCellStyle();
		CellStyle currencyStyle = workbook.createCellStyle();
		CellStyle dateStyle = workbook.createCellStyle();

		// Set up cell styles
		XSSFDataFormat df = workbook.createDataFormat();
		currencyStyle.setDataFormat(df.getFormat("$#,##0.00;$ (#,##0.00)"));
		dateStyle.setDataFormat(df.getFormat("m/d/yy"));

		// Begin to aggregate data and write it
		for (Company customer : this.customers) {
			// Set the variables to hold the total units sold and total sales for client\
			int units = 0;
			BigDecimal sales = new BigDecimal("0.0");
			BigDecimal received = new BigDecimal("0.0");

			for (CustomerOrder order : customer.getOrders()) {

				// Add up the units and sales from all orders
				sales = sales.add(order.getSubtotal().add(order.getShipping()));
				received = received.add(order.getReceived());
				for (OrderItem item : order.getItems()) {
					units += item.getUnits();
				}
			}

			// Begin to write data
			int orderColCount = 0;
			Row orderRow = sheet.createRow(rowCount++);
			CellMaker.createCell(sheet, orderRow, orderColCount++, customer.getId(), style);
			CellMaker.createCell(sheet, orderRow, orderColCount++, customer.getName(), style);
			CellMaker.createCell(sheet, orderRow, orderColCount++, units, style);
			CellMaker.createCell(sheet, orderRow, orderColCount++, sales, currencyStyle);
			CellMaker.createCell(sheet, orderRow, orderColCount++, received, currencyStyle);
			CellMaker.createCell(sheet, orderRow, orderColCount++, customer.getOrders().size(), style);
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
