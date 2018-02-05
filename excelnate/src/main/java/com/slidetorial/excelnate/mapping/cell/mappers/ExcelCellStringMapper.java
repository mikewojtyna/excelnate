/**
 *
 */
package com.slidetorial.excelnate.mapping.cell.mappers;

import static com.google.common.base.Preconditions.checkNotNull;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import com.slidetorial.excelnate.mapping.cell.ExcelCellMapper;
import com.slidetorial.excelnate.mapping.cell.ExcelCellMapperException;

/**
 * The implementation of ExcelCellMapper which maps content of the cells into
 * {@code String} values.
 *
 * @author goobar
 *
 */
public class ExcelCellStringMapper implements ExcelCellMapper<String>
{

	/**
	 * Returns the string value of this cell without leading and trailing
	 * whitespaces. This method automatically converts number values.
	 */
	@Override
	public String map(Cell cell)
		throws NullPointerException, ExcelCellMapperException
	{
		validate(cell);
		switch (cell.getCellTypeEnum())
		{
			case STRING:
				return cell.getStringCellValue().trim();
			case NUMERIC:
				return convertNumericToString(cell);
			default:
				throw new ExcelCellMapperException(
					ExcelCellMapperExceptionMessageBuilder
						.buildExceptionCellNotSupportedMsg(
							cell, String.class));
		}
	}

	/**
	 * @param cell
	 * @return
	 */
	private String convertNumericToString(Cell cell)
	{
		return new DataFormatter().formatCellValue(cell);
	}

	/**
	 * @param cell
	 */
	private void validate(Cell cell)
	{
		checkNotNull(cell, "'cell' cannot be null");
	}

}
