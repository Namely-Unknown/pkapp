package com.plantkeeper.export;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

// NOTATED
/**
 * The Parent Class for all Excel Documents that will be generated
 * @author bobde
 *
 */
public abstract class ExcelExporter {
	private XSSFWorkbook workbook;

	public abstract void writeHeaderLine();
	public abstract void writeDataLines();

	public void export(HttpServletResponse response) throws IOException {
		writeHeaderLine();
		writeDataLines();

		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();

		outputStream.close();
	}
}
