/**
 *
 */
package com.slidetorial.excelnate.mapping.cell.mappers;

import static com.google.common.base.Preconditions.checkNotNull;
import java.time.LocalDate;
import java.time.ZoneId;
import org.apache.poi.ss.usermodel.Cell;
import com.slidetorial.excelnate.mapping.cell.ExcelCellMapper;
import com.slidetorial.excelnate.mapping.cell.ExcelCellMapperException;

/**
 * @author goobar
 *
 */
public class ExcelCellLocalDateMapper implements ExcelCellMapper<LocalDate>
{

	/* (non-Javadoc)
	 * @see com.onezerobinary.certify.excel.reader.mapping.ExcelCellMapper#map(org.apache.poi.ss.usermodel.Cell)
	 */
	@Override
	public LocalDate map(Cell cell)
		throws NullPointerException, ExcelCellMapperException
	{
		validate(cell);
		try
		{
			switch (cell.getCellTypeEnum())
			{
				case NUMERIC:
					return convertCellToLocalDate(cell);

				default:
					throw createCellTypeNotSupportedException(
						cell);
			}

		}
		catch (IllegalStateException | NumberFormatException e)
		{
			throw wrapException(cell, e);
		}
	}

	/**
	 * @param cell
	 * @return
	 */
	private LocalDate convertCellToLocalDate(Cell cell)
	{
		return cell.getDateCellValue().toInstant()
			.atZone(ZoneId.systemDefault()).toLocalDate();
	}

	/**
	 * @param cell
	 * @return
	 */
	private ExcelCellMapperException createCellTypeNotSupportedException(
		Cell cell)
	{
		return ExcelCellMapperExceptionUtils
			.createCellTypeNotSupportedException(cell,
				LocalDate.class);
	}

	/**
	 * @param cell
	 */
	private void validate(Cell cell)
	{
		checkNotNull(cell, "'cell' cannot be null");
	}

	/**
	 * @param cell
	 * @param e
	 * @return
	 */
	private ExcelCellMapperException wrapException(Cell cell,
		RuntimeException e)
	{
		return ExcelCellMapperExceptionUtils.wrapException(cell,
			LocalDate.class, e);
	}

}
