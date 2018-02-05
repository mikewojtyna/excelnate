/**
 *
 */
package com.slidetorial.excelnate.testobjects;

import java.time.LocalDate;
import com.slidetorial.excelnate.mapping.cell.ExcelCell;
import com.slidetorial.excelnate.mapping.cell.ExcelSource;
import com.slidetorial.excelnate.mapping.cell.mappers.ExcelCellDoubleMapper;
import com.slidetorial.excelnate.mapping.cell.mappers.ExcelCellLocalDateMapper;
import com.slidetorial.excelnate.mapping.cell.mappers.ExcelCellStringMapper;
import com.slidetorial.excelnate.mapping.row.ExcelObject;
import com.slidetorial.excelnate.mapping.row.SourceDocuments;

/**
 * A test class used by mapping scenarios.
 *
 * @author goobar
 *
 */
@SuppressWarnings("javadoc")
public class TestObject
{

	public static final String SOURCE_DOCUMENT_0 = "doc0";

	public static final String SOURCE_DOCUMENT_1 = "doc1";

	@ExcelObject
	private TestNestedObject complexField;

	@ExcelCell(cellMapper = ExcelCellLocalDateMapper.class,
		sources = { @ExcelSource(index = 1,
			source = SourceDocuments.DEFAULT_SOURCE_DOCUMENT) })
	private LocalDate dateField;

	@ExcelCell(cellMapper = ExcelCellDoubleMapper.class,
		sources = { @ExcelSource(index = 2,
			source = SourceDocuments.DEFAULT_SOURCE_DOCUMENT) })
	private double doubleField;

	@ExcelCell(cellMapper = ExcelCellLocalDateMapper.class,
		sources = { @ExcelSource(index = 37,
			source = SourceDocuments.DEFAULT_SOURCE_DOCUMENT) })
	private LocalDate secondDateField;

	@ExcelCell(cellMapper = ExcelCellDoubleMapper.class,
		sources = { @ExcelSource(index = 250,
			source = SourceDocuments.DEFAULT_SOURCE_DOCUMENT) })
	private double secondDoubleField;

	@ExcelCell(cellMapper = ExcelCellStringMapper.class,
		sources = { @ExcelSource(index = 10,
			source = SourceDocuments.DEFAULT_SOURCE_DOCUMENT) })
	private String secondStringField;

	@ExcelCell(cellMapper = ExcelCellStringMapper.class,
		sources = {
			@ExcelSource(index = 0,
				source = SourceDocuments.DEFAULT_SOURCE_DOCUMENT),
			@ExcelSource(index = 15, source = "doc0"),
			@ExcelSource(index = 20, source = "doc1") })
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
		TestObject other = (TestObject) obj;
		if (complexField == null)
		{
			if (other.complexField != null)
			{
				return false;
			}
		}
		else if (!complexField.equals(other.complexField))
		{
			return false;
		}
		if (dateField == null)
		{
			if (other.dateField != null)
			{
				return false;
			}
		}
		else if (!dateField.equals(other.dateField))
		{
			return false;
		}
		if (Double.doubleToLongBits(doubleField) != Double
			.doubleToLongBits(other.doubleField))
		{
			return false;
		}
		if (secondDateField == null)
		{
			if (other.secondDateField != null)
			{
				return false;
			}
		}
		else if (!secondDateField.equals(other.secondDateField))
		{
			return false;
		}
		if (Double.doubleToLongBits(secondDoubleField) != Double
			.doubleToLongBits(other.secondDoubleField))
		{
			return false;
		}
		if (secondStringField == null)
		{
			if (other.secondStringField != null)
			{
				return false;
			}
		}
		else if (!secondStringField.equals(other.secondStringField))
		{
			return false;
		}
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
	 * @return the complexField
	 */
	public TestNestedObject getComplexField()
	{
		return complexField;
	}

	/**
	 * @return the dateField
	 */
	public LocalDate getDateField()
	{
		return dateField;
	}

	/**
	 * @return the doubleField
	 */
	public double getDoubleField()
	{
		return doubleField;
	}

	/**
	 * @return the secondDateField
	 */
	public LocalDate getSecondDateField()
	{
		return secondDateField;
	}

	/**
	 * @return the secondDoubleField
	 */
	public double getSecondDoubleField()
	{
		return secondDoubleField;
	}

	/**
	 * @return the secondStringField
	 */
	public String getSecondStringField()
	{
		return secondStringField;
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
		result = prime * result + ((complexField == null) ? 0
			: complexField.hashCode());
		result = prime * result
			+ ((dateField == null) ? 0 : dateField.hashCode());
		long temp;
		temp = Double.doubleToLongBits(doubleField);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((secondDateField == null) ? 0
			: secondDateField.hashCode());
		temp = Double.doubleToLongBits(secondDoubleField);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((secondStringField == null) ? 0
			: secondStringField.hashCode());
		result = prime * result
			+ ((stringField == null) ? 0 : stringField.hashCode());
		return result;
	}

	/**
	 * @param complexField
	 *                the complexField to set
	 */
	public void setComplexField(TestNestedObject complexField)
	{
		this.complexField = complexField;
	}

	/**
	 * @param dateField
	 *                the dateField to set
	 */
	public void setDateField(LocalDate dateField)
	{
		this.dateField = dateField;
	}

	/**
	 * @param doubleField
	 *                the doubleField to set
	 */
	public void setDoubleField(double doubleField)
	{
		this.doubleField = doubleField;
	}

	/**
	 * @param secondDateField
	 *                the secondDateField to set
	 */
	public void setSecondDateField(LocalDate secondDateField)
	{
		this.secondDateField = secondDateField;
	}

	/**
	 * @param secondDoubleField
	 *                the secondDoubleField to set
	 */
	public void setSecondDoubleField(double secondDoubleField)
	{
		this.secondDoubleField = secondDoubleField;
	}

	/**
	 * @param secondStringField
	 *                the secondStringField to set
	 */
	public void setSecondStringField(String secondStringField)
	{
		this.secondStringField = secondStringField;
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
