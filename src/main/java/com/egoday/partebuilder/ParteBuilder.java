package com.egoday.partebuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class ParteBuilder {

	private static final String TEMPLATE = "/template.xls";

	private static final int SHEET_INDEX = 0;

	private static final int NAME_ROW = 1;

	private static final int NAME_COL = 5;

	private static final int CLIENT_ROW = 4;

	private static final int CLIENT_COL = 5;

	private static final int START_ROW = 8;

	private static final int DAY_COL = 0;

	private static final int CLIENT2_COL = 1;

	private static final int PLACE_COL = 2;

	private static final int SCHED_COL = 3;

	private static final int HOURS_COL = 4;

	public void build(OutputStream outputStream, Model model) throws IOException {
		InputStream inputStream = ParteBuilder.class.getResourceAsStream(TEMPLATE);

		HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
		HSSFSheet sheet = workbook.getSheetAt(SHEET_INDEX);
		workbook.setSheetName(SHEET_INDEX, model.getMonthName());

		Row row = sheet.getRow(NAME_ROW);
		Cell cell = row.getCell(NAME_COL, Row.CREATE_NULL_AS_BLANK);
		cell.setCellValue(model.getName());

		row = sheet.getRow(CLIENT_ROW);
		cell = row.getCell(CLIENT_COL, Row.CREATE_NULL_AS_BLANK);
		cell.setCellValue(model.getClient());

		int rowIndex = START_ROW;

		Date date = DateUtils.truncate(model.getDate(), Calendar.MONTH);

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		int month = calendar.get(Calendar.MONTH);
		int dayOfWeek = 0;

		while (calendar.get(Calendar.MONTH) == month) {
			dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

			if (dayOfWeek > Calendar.SUNDAY && dayOfWeek < Calendar.SATURDAY) {
				row = sheet.getRow(rowIndex);
				cell = row.getCell(DAY_COL, Row.CREATE_NULL_AS_BLANK);
				cell.setCellValue(calendar);

				cell = row.getCell(CLIENT2_COL, Row.CREATE_NULL_AS_BLANK);
				cell.setCellValue(StringUtils.capitalize(model.getClient().toLowerCase()));

				cell = row.getCell(PLACE_COL, Row.CREATE_NULL_AS_BLANK);
				cell.setCellValue(model.getPlace());

				cell = row.getCell(SCHED_COL, Row.CREATE_NULL_AS_BLANK);

				if (dayOfWeek != Calendar.FRIDAY) {
					cell.setCellValue(model.getHours());
				} else {
					cell.setCellValue(model.getFridayHours());
				}

				cell = row.getCell(HOURS_COL, Row.CREATE_NULL_AS_BLANK);

				if (dayOfWeek != Calendar.FRIDAY) {
					cell.setCellValue(model.getHoursCount());
				} else {
					cell.setCellValue(model.getFridayHoursCount());
				}

				rowIndex++;
			}

			calendar.add(Calendar.DATE, 1);
		}

		workbook.write(outputStream);

		outputStream.close();
		workbook.close();
	}

}
