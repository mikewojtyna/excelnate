/**
 *
 */
package com.slidetorial.excelnate.mapping.cell;

import org.apache.poi.ss.usermodel.Cell;

/**
 * A generic mapper interface to map Excel cells into data object fields.
 *
 * @author goobar
 * @param <T>
 *                type of the data class
 *
 */
public interface ExcelCellMapper<T>
{
	/**
	 * Maps the content of a single cell of the Excel document into a target
	 * data type {@code T}.
	 *
	 * @param cell
	 *                an Excel cell
	 * @return the mapped data class
	 * @throws NullPointerException
	 *                 if any argument is null
	 * @throws ExcelCellMapperException
	 *                 when cell mapping failed
	 */
	T map(Cell cell) throws NullPointerException, ExcelCellMapperException;
}
