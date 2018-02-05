/**
 *
 */
package com.slidetorial.excelnate.mapping.cell;

import java.io.IOException;
import java.util.Calendar;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author goobar
 *
 */
@SuppressWarnings("javadoc")
public class CellFixtureUtils
{

	public static Cell blankCell()
	{
		return createCellOfType(CellType.BLANK);
	}

	public static Cell dateCell(int year, int month, int day)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, day);
		Cell cell = createCellOfType(CellType.NUMERIC);
		cell.setCellValue(calendar.getTime());
		return cell;
	}

	public static Cell numberCell(double value)
	{
		Cell cell = createCellOfType(CellType.NUMERIC);
		cell.setCellValue(value);
		return cell;
	}

	public static Cell stringCell(String content)
	{
		Cell cell = createCellOfType(CellType.STRING);
		cell.setCellValue(content);
		return cell;
	}

	private static Cell createCellOfType(CellType numeric)
	{
		try (Workbook workbook = new XSSFWorkbook())
		{
			return workbook.createSheet().createRow(0).createCell(0,
				CellType.NUMERIC);
		}
		catch (IOException e)
		{
			throw new RuntimeException(e);
		}
	}

}
