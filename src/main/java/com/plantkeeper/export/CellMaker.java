package com.plantkeeper.export;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;

// NOTATED
/**
 * A class used to generate Cells for an Excel document being generated for export
 * @author bobde
 *
 */
public class CellMaker {

	/**
	 * Create a Cell
	 * @param sheet to add the cell to
	 * @param row in which the cell is located
	 * @param columnCount The column in which the cell is located
	 * @param value of the cell
	 * @param style to place in the cell
	 */
	public static void createCell(XSSFSheet sheet, Row row, int columnCount, Object value, CellStyle style) {
		sheet.autoSizeColumn(columnCount);
		Cell cell = row.createCell(columnCount);
		if (value instanceof Integer) {
			cell.setCellValue((Integer) value);
		} else if (value instanceof Long) {
			cell.setCellValue(((Long) value).doubleValue());
		} else if (value instanceof LocalDate) {
			ZoneId defaultZoneId = ZoneId.systemDefault();
			Date date = Date.from(((LocalDate) value).atStartOfDay(defaultZoneId).toInstant());
			cell.setCellValue(date);
		} else if (value instanceof BigDecimal) {
			cell.setCellValue(((BigDecimal) value).doubleValue());
		} else {
			cell.setCellValue((String) value);
		}
		cell.setCellStyle(style);
	}
}
