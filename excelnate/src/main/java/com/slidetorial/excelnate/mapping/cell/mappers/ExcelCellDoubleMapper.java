/**
 *
 */
package com.slidetorial.excelnate.mapping.cell.mappers;

import static com.google.common.base.Preconditions.checkNotNull;
import org.apache.poi.ss.usermodel.Cell;
import com.slidetorial.excelnate.mapping.cell.ExcelCellMapper;
import com.slidetorial.excelnate.mapping.cell.ExcelCellMapperException;

/**
 * @author goobar
 *
 */
public class ExcelCellDoubleMapper implements ExcelCellMapper<Double>
{

	/* (non-Javadoc)
	 * @see com.onezerobinary.certify.excel.reader.mapping.ExcelCellMapper#map(org.apache.poi.ss.usermodel.Cell)
	 */
	@Override
	public Double map(Cell cell)
		throws NullPointerException, ExcelCellMapperException
	{
		validate(cell);
		try
		{
			switch (cell.getCellTypeEnum())
			{
				case NUMERIC:
					return convertCellToDouble(cell);

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
	private Double convertCellToDouble(Cell cell)
	{
		return cell.getNumericCellValue();
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
				Double.class);
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
			Double.class, e);
	}

}
