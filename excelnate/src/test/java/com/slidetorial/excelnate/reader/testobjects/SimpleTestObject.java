/**
 *
 */
package com.slidetorial.excelnate.reader.testobjects;

import com.slidetorial.excelnate.mapping.cell.ExcelCell;
import com.slidetorial.excelnate.mapping.cell.ExcelSource;
import com.slidetorial.excelnate.mapping.cell.mappers.ExcelCellStringMapper;
import com.slidetorial.excelnate.mapping.row.SourceDocuments;

/**
 * @author goobar
 *
 */
public class SimpleTestObject
{
	@ExcelCell(cellMapper = ExcelCellStringMapper.class,
		sources = { @ExcelSource(index = 0,
			source = SourceDocuments.DEFAULT_SOURCE_DOCUMENT) })
	private String stringField;

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj == null)
		{
			return false;
		}
		if (getClass() != obj.getClass())
		{
			return false;
		}
		SimpleTestObject other = (SimpleTestObject) obj;
		if (stringField == null)
		{
			if (other.stringField != null)
			{
				return false;
			}
		}
		else if (!stringField.equals(other.stringField))
		{
			return false;
		}
		return true;
	}

	/**
	 * @return the stringField
	 */
	public String getStringField()
	{
		return stringField;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result
			+ ((stringField == null) ? 0 : stringField.hashCode());
		return result;
	}

	/**
	 * @param stringField
	 *                the stringField to set
	 */
	public void setStringField(String stringField)
	{
		this.stringField = stringField;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "SimpleTestObject [stringField=" + stringField + "]";
	}
}
