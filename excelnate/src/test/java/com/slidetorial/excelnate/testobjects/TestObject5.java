/**
 *
 */
package com.slidetorial.excelnate.testobjects;

import com.slidetorial.excelnate.mapping.row.ExcelObject;

/**
 * @author goobar
 *
 */
public class TestObject5
{
	@ExcelObject
	private TestAbstractClass complexField;

	/**
	 * @return the complexField
	 */
	public TestAbstractClass getComplexField()
	{
		return complexField;
	}

	/**
	 * @param complexField
	 *                the complexField to set
	 */
	public void setComplexField(TestAbstractClass complexField)
	{
		this.complexField = complexField;
	}
}
