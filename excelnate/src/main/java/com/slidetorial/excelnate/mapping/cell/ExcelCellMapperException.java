/**
 *
 */
package com.slidetorial.excelnate.mapping.cell;

/**
 * Root class of all exceptions thrown by {@link ExcelCellMapper}.
 * 
 * @author goobar
 *
 */
@SuppressWarnings("javadoc")
public class ExcelCellMapperException extends RuntimeException
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public ExcelCellMapperException()
	{
		super();
	}

	public ExcelCellMapperException(String message)
	{
		super(message);
	}

	public ExcelCellMapperException(String message, Throwable cause)
	{
		super(message, cause);
	}

}
