/**
 *
 */
package com.slidetorial.excelnate.testobjects;

import com.slidetorial.excelnate.mapping.cell.ExcelCell;
import com.slidetorial.excelnate.mapping.cell.ExcelSource;
import com.slidetorial.excelnate.mapping.cell.mappers.ExcelCellStringMapper;
import com.slidetorial.excelnate.mapping.row.SourceDocuments;

/**
 * @author goobar
 *
 */
public class TestObject2
{

	@ExcelCell(cellMapper = ExcelCellStringMapper.class,
		sources = { @ExcelSource(index = 0,
			source = SourceDocuments.DEFAULT_SOURCE_DOCUMENT) })
	private String field;

	/**
	 * @return the field
	 */
	public String getField()
	{
		return field;
	}

}
