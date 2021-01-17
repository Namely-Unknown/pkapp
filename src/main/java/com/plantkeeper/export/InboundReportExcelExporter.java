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

import com.plantkeeper.entity.CustomerOrder;
import com.plantkeeper.entity.OrderItem;
import com.plantkeeper.entity.Shipment;
import com.plantkeeper.entity.ShipmentItem;

public class InboundReportExcelExporter extends ExcelExporter {

	private XSSFWorkbook workbook;
	private XSSFSheet shipmentSheet;
	private XSSFSheet itemSheet;
	private List<Shipment> shipments;

	public InboundReportExcelExporter(List<Shipment> shipmentList) {
		this.shipments = shipmentList;
		workbook = new XSSFWorkbook();
	}

	@Override
	public void writeHeaderLine() {
		// TODO Auto-generated method stub
		shipmentSheet = workbook.createSheet("Inbound Data");

		Row row = shipmentSheet.createRow(0);

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setFontHeight(12);
		font.setColor(IndexedColors.WHITE.getIndex());
		// style.setBorderBottom(BorderStyle.MEDIUM);
		style.setFillForegroundColor(IndexedColors.BLUE_GREY.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setFont(font);

		int colNumber = 0;

		CellMaker.createCell(shipmentSheet, row, colNumber++, "Shipment ID", style);
		CellMaker.createCell(shipmentSheet, row, colNumber++, "Shipment Order Date", style);
		CellMaker.createCell(shipmentSheet, row, colNumber++, "Shipping Co", style);
		CellMaker.createCell(shipmentSheet, row, colNumber++, "Plant", style);
		CellMaker.createCell(shipmentSheet, row, colNumber++, "Container", style);
		CellMaker.createCell(shipmentSheet, row, colNumber++, "Units", style);
		CellMaker.createCell(shipmentSheet, row, colNumber++, "Unit Price", style);

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
		for (Shipment shipment : shipments) {
			for (ShipmentItem item : shipment.getItems()) {
				int orderColCount = 0;
				Row orderRow = shipmentSheet.createRow(orderRowCount++);
				CellMaker.createCell(shipmentSheet, orderRow, orderColCount++, shipment.getId(), style);
				CellMaker.createCell(shipmentSheet, orderRow, orderColCount++, shipment.getOrdered(), dateStyle);
				CellMaker.createCell(shipmentSheet, orderRow, orderColCount++, shipment.getShipper().getName(), style);
				CellMaker.createCell(shipmentSheet, orderRow, orderColCount++, item.getProduct().getPlant().getName() , style);
				CellMaker.createCell(shipmentSheet, orderRow, orderColCount++, item.getProduct().getContainer().getName(), style);
				CellMaker.createCell(shipmentSheet, orderRow, orderColCount++, item.getUnits(), style);
				CellMaker.createCell(shipmentSheet, orderRow, orderColCount++, item.getUnitPrice(), currencyStyle);
			}
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
