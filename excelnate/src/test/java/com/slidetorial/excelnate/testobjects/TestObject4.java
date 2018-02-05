/**
 *
 */
package com.slidetorial.excelnate.testobjects;

import com.slidetorial.excelnate.mapping.row.ExcelObject;

/**
 * @author goobar
 *
 */
public class TestObject4
{
	@ExcelObject
	private TestInterface complexField;

	/**
	 * @return the complexField
	 */
	public TestInterface getComplexField()
	{
		return complexField;
	}

	/**
	 * @param complexField
	 *                the complexField to set
	 */
	public void setComplexField(TestInterface complexField)
	{
		this.complexField = complexField;
	}
}
