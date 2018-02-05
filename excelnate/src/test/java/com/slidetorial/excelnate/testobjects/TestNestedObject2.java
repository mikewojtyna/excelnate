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
public class TestNestedObject2
{
	@ExcelCell(cellMapper = ExcelCellStringMapper.class,
		sources = { @ExcelSource(index = 1,
			source = SourceDocuments.DEFAULT_SOURCE_DOCUMENT) })
	private String stringField;

	/**
	 * @return the stringField
	 */
	public String getStringField()
	{
		return stringField;
	}

	/**
	 * @param stringField
	 *                the stringField to set
	 */
	public void setStringField(String stringField)
	{
		this.stringField = stringField;
	}
}
