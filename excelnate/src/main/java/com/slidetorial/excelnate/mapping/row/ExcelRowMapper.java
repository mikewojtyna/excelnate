/**
 *
 */
package com.slidetorial.excelnate.mapping.row;

import org.apache.poi.ss.usermodel.Row;
import com.slidetorial.excelnate.mapping.cell.ExcelCell;

/**
 * The main component of the Excel data mapping system. Use it to map Excel row
 * into data object. The goal of this component is to traverse the Excel data
 * mapping class and set values of all cell fields. Cell field is a field of the
 * data class, which corresponding setter is annotated with {@link ExcelCell},
 * so it can be mapped directly from the content of the Excel cell.
 *
 * @author goobar
 * @param <D>
 *                data class type
 *
 */
public interface ExcelRowMapper<D>
{
	/**
	 * Maps the Excel row to data object. This method maps cells of the row
	 * based on the {@link ExcelCell} mapping annotations. If data class has
	 * no {@link ExcelCell} mapping, then this method simply returns the
	 * default instance.
	 *
	 * @param row
	 *                an Excel row
	 * @return the mapped data class
	 * @throws NullPointerException
	 *                 if any argument is null
	 * @throws ExcelRowMapperException
	 *                 if mapper failed
	 */
	D map(Row row)
		throws NullPointerException, ExcelRowMapperException;
}
