package com.egoday.partebuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class App {

	private static final String OUT = "C:/Users/Emiliano/OneDrive/Partes New/%1$tY/Parte %2$s %1$tB.xls";

	private static final String NAME = "EMILIANO GODAY";

	public static void main(String... args) throws IOException {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, 0);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		int year = cal.get(Calendar.YEAR);

		while (year == cal.get(Calendar.YEAR)) {
			String fileName = String.format(OUT, cal.getTime(), NAME);
			File file = new File(fileName);

			File dir = file.getParentFile();
			if (!dir.exists()) {
				dir.mkdirs();
			}

			FileOutputStream outputStream = new FileOutputStream(fileName);

			boolean summerIsComming = cal.get(Calendar.MONTH) == Calendar.JULY ||
					cal.get(Calendar.MONTH) == Calendar.AUGUST;

			Model model;
			if (summerIsComming) {
				model = new Model(cal.getTime(), NAME, "ATOS",
						"Santiago",
						"8:00-15:00",
						"8:00-15:00",
						7,
						7);
			} else {
				model = new Model(cal.getTime(), NAME, "ATOS",
						"Santiago",
						"8:00-15:00, 16:00-17:30",
						"8:00-14:00",
						8.5,
						6);
			}

			ParteBuilder parteBuilder = new ParteBuilder();
			parteBuilder.build(outputStream, model);

			cal.add(Calendar.MONTH, 1);
		}
	}

}
