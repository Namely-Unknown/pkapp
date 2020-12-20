//package com.plantkeeper.export;
//
//import java.io.IOException;
//import java.math.BigDecimal;
//import java.util.List;
//
//import javax.servlet.ServletOutputStream;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.poi.ss.usermodel.CellStyle;
//import org.apache.poi.ss.usermodel.FillPatternType;
//import org.apache.poi.ss.usermodel.IndexedColors;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.xssf.usermodel.XSSFDataFormat;
//import org.apache.poi.xssf.usermodel.XSSFFont;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//import com.plantkeeper.entity.CustomerOrder;
//import com.plantkeeper.entity.OrderItem;
//
//// NOTATED
//
///**
// * Base Export to Excel for data related to a single client
// * @author bobde
// *
// */
//public class ClientExcelExporter extends ExcelExporter{
//
//	private XSSFWorkbook workbook;
//	private XSSFSheet orderSheet;
//	private XSSFSheet itemSheet;
//	private List<CustomerOrder> orders;
//
//	public ClientExcelExporter(List<CustomerOrder> orderList) {
//		this.orders = orderList;
//		workbook = new XSSFWorkbook();
//	}
//
//	public void writeHeaderLine() {
//
//		// Create the Sheet for the orders
//		orderSheet = workbook.createSheet("Order Data");
//
//		Row row = orderSheet.createRow(0);
//
//		CellStyle style = workbook.createCellStyle();
//		XSSFFont font = workbook.createFont();
//		font.setFontHeight(12);
//		font.setColor(IndexedColors.WHITE.getIndex());
//		// style.setBorderBottom(BorderStyle.MEDIUM);
//		style.setFillForegroundColor(IndexedColors.BLUE_GREY.getIndex());
//		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
//		style.setFont(font);
//
//		int colNumber = 0;
//
//		CellMaker.createCell(orderSheet, row, colNumber++, "Order ID", style);
//		CellMaker.createCell(orderSheet, row, colNumber++, "Creation Date", style);
//		CellMaker.createCell(orderSheet, row, colNumber++, "PO Number", style);
//		CellMaker.createCell(orderSheet, row, colNumber++, "Status", style);
//		CellMaker.createCell(orderSheet, row, colNumber++, "SubTotal", style);
//		CellMaker.createCell(orderSheet, row, colNumber++, "Shipping", style);
//		CellMaker.createCell(orderSheet, row, colNumber++, "Paid Date", style);
//		CellMaker.createCell(orderSheet, row, colNumber++, "Paid Amount", style);
//
//		// Create sheet for the order items
//		itemSheet = workbook.createSheet("Order Item Data");
//
//		row = itemSheet.createRow(0);
//
//		colNumber = 0;
//
//		CellMaker.createCell(itemSheet, row, colNumber++, "Order ID", style);
//		CellMaker.createCell(itemSheet, row, colNumber++, "Creation Date", style);
//		CellMaker.createCell(itemSheet, row, colNumber++, "PO Number", style);
//		CellMaker.createCell(itemSheet, row, colNumber++, "Status", style);
//		CellMaker.createCell(itemSheet, row, colNumber++, "Paid Date", style);
//		CellMaker.createCell(itemSheet, row, colNumber++, "Plant", style);
//		CellMaker.createCell(itemSheet, row, colNumber++, "Category", style);
//		CellMaker.createCell(itemSheet, row, colNumber++, "Container", style);
//		CellMaker.createCell(itemSheet, row, colNumber++, "Units", style);
//		CellMaker.createCell(itemSheet, row, colNumber++, "Price", style);
//		CellMaker.createCell(itemSheet, row, colNumber++, "SubTotal", style);
//	}
//
//	public void writeDataLines() {
//		int itemRowCount = 1;
//		int orderRowCount = 1;
//		// Prep my cell styles
//		CellStyle style = workbook.createCellStyle();
//		CellStyle currencyStyle = workbook.createCellStyle();
//		CellStyle dateStyle = workbook.createCellStyle();
//
//		// Set up cell styles
//		XSSFDataFormat df = workbook.createDataFormat();
//		currencyStyle.setDataFormat(df.getFormat("$#,##0.00;$ (#,##0.00)"));
//		dateStyle.setDataFormat(df.getFormat("m/d/yy"));
//
//		// Write the Order Data
//		for (CustomerOrder order : orders) {
//			int orderColCount = 0;
//			Row orderRow = orderSheet.createRow(orderRowCount++);
//			CellMaker.createCell(orderSheet, orderRow, orderColCount++, order.getId(), style);
//			CellMaker.createCell(orderSheet, orderRow, orderColCount++, order.getCreated(), dateStyle);
//			CellMaker.createCell(orderSheet, orderRow, orderColCount++, order.getPoNumber(), style);
//			CellMaker.createCell(orderSheet, orderRow, orderColCount++, order.getStatus().toString(), style);
//			//CellMaker.createCell(orderSheet, orderRow, orderColCount++, order.getSubTotal(), currencyStyle);
//			CellMaker.createCell(orderSheet, orderRow, orderColCount++, order.getShipping(), currencyStyle);
//			CellMaker.createCell(orderSheet, orderRow, orderColCount++, order.getPaidDate(), dateStyle);
//			CellMaker.createCell(orderSheet, orderRow, orderColCount++, order.getReceived(), currencyStyle);
//			
//
//			for (OrderItem item : order.getOrderItems()) {
//				Row row = itemSheet.createRow(itemRowCount++);
//				int columnCount = 0;
//
//				CellMaker.createCell(itemSheet, row, columnCount++, order.getId(), style);
//				CellMaker.createCell(itemSheet, row, columnCount++, order.getCreated(), dateStyle);
//				CellMaker.createCell(itemSheet, row, columnCount++, order.getPoNumber(), style);
//				CellMaker.createCell(itemSheet, row, columnCount++, order.getStatus().toString(), style);
//				CellMaker.createCell(itemSheet, row, columnCount++, order.getPaidDate(), dateStyle);
//				CellMaker.createCell(itemSheet, row, columnCount++, item.getProduct().getPlant().getName(), style);
//				CellMaker.createCell(itemSheet, row, columnCount++, item.getProduct().getPlant().getCategory().getName(), style);
//				CellMaker.createCell(itemSheet, row, columnCount++, item.getProduct().getContainer().getName(), style);
//				CellMaker.createCell(itemSheet, row, columnCount++, item.getUnits(), style);
//				CellMaker.createCell(itemSheet, row, columnCount++, item.getUnitPrice(), currencyStyle);
//				CellMaker.createCell(itemSheet, row, columnCount++, item.getUnitPrice().multiply(BigDecimal.valueOf(item.getUnits())),
//						currencyStyle);
//
//			}
//		}
//
//	}
//
//	public void export(HttpServletResponse response) throws IOException {
//		writeHeaderLine();
//		writeDataLines();
//
//		ServletOutputStream outputStream = response.getOutputStream();
//		workbook.write(outputStream);
//		workbook.close();
//
//		outputStream.close();
//
//	}
//}
