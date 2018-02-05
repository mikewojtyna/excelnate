/**
 *
 */
package com.slidetorial.excelnate.mapping.row;

/**
 * A root class for all {@link ExcelRowMapper}
 * exceptions.
 *
 * @author goobar
 *
 */
@SuppressWarnings("javadoc")
public class ExcelRowMapperException
	extends RuntimeException
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public ExcelRowMapperException()
	{
		super();
	}

	public ExcelRowMapperException(
		String message)
	{
		super(message);
	}

	public ExcelRowMapperException(
		String message, Throwable cause)
	{
		super(message, cause);
	}

}
