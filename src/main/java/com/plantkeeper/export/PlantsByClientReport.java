//package com.plantkeeper.export;
//
//import java.io.IOException;
//import java.math.BigDecimal;
//import java.time.LocalDate;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
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
//import com.plantkeeper.business.PlantData;
//import com.plantkeeper.entity.Customer;
//import com.plantkeeper.entity.CustomerOrder;
//import com.plantkeeper.entity.OrderItem;
//import com.plantkeeper.entity.Plant;
//
//// NOTATED
///**
// * A class to generate reports on Plant sales by Client
// * @author bobde
// *
// */
//public class PlantsByClientReport extends ExcelExporter {
//
//	private XSSFWorkbook workbook;
//	private XSSFSheet sheet;
//	private List<Customer> customers;
//
//	public PlantsByClientReport(List<Customer> customerList) {
//		this.customers = customerList;
//		workbook = new XSSFWorkbook();
//	}
//
//	public void writeHeaderLine() {
//
//		// Create the Sheet for the orders
//		sheet = workbook.createSheet("Sales Data");
//
//		Row row = sheet.createRow(0);
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
//		CellMaker.createCell(sheet, row, colNumber++, "Client ID", style);
//		CellMaker.createCell(sheet, row, colNumber++, "Client Name", style);
//		CellMaker.createCell(sheet, row, colNumber++, "Plant Name", style);
//		CellMaker.createCell(sheet, row, colNumber++, "Units Sold", style);
//		CellMaker.createCell(sheet, row, colNumber++, "Revenue", style);
//		CellMaker.createCell(sheet, row, colNumber++, "Last Purchase", style);
//	}
//
//	public void writeDataLines() {
//		int rowCount = 1;
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
//		// Begin to aggregate data and write it
//		for (Customer customer : this.customers) {
//
//			Map<Long, PlantData> plantMap = new HashMap<>();
//
//			for (CustomerOrder order : customer.getOrders()) {
//
//				// Get the plants
//				for (OrderItem item : order.getOrderItems()) {
//
//					Plant plant = item.getProduct().getPlant();
//					Long id = plant.getId();
//					String name = plant.getName();
//					LocalDate purchase = item.getOrder().getCreated();
//					int units = item.getUnits();
//					BigDecimal sales = item.getPrice().multiply(BigDecimal.valueOf(units));
//
//					PlantData p;
//
//					if (plantMap.containsKey(id)) {
//						p = plantMap.get(id);
//						p.setSales(p.getSales().add(sales));
//						p.setUnits(p.getUnits() + units);
//						if (purchase.isAfter(p.getLastPurchase())) {
//							p.setLastPurchase(purchase);
//							p.setLastPurchaseId(order.getId());
//						}
//
//					} else {
//						p = new PlantData(id, name, purchase, order.getId(), units, sales);
//					}
//
//					// Put the plant data into the plantmap
//					plantMap.put(id, p);
//				}
//
//				// Begin to write data
//
//			}
//			for (PlantData data : plantMap.values()) {
//				int orderColCount = 0;
//				Row orderRow = sheet.createRow(rowCount++);
//				CellMaker.createCell(sheet, orderRow, orderColCount++, customer.getId(), style);
//				CellMaker.createCell(sheet,orderRow, orderColCount++, customer.getName(), style);
//				CellMaker.createCell(sheet,orderRow, orderColCount++, data.getName(), style);
//				CellMaker.createCell(sheet,orderRow, orderColCount++, data.getUnits(), style);
//				CellMaker.createCell(sheet,orderRow, orderColCount++, data.getSales(), currencyStyle);
//				CellMaker.createCell(sheet,orderRow, orderColCount++, data.getLastPurchase(), dateStyle);
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
//
//}
