/**
 *
 */
package com.slidetorial.excelnate.reader;

/**
 * A base class for all {@link ExcelReader} exceptions.
 *
 * @author goobar
 *
 */
@SuppressWarnings("javadoc")
public class ExcelReaderException extends RuntimeException
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public ExcelReaderException()
	{
		super();
	}

	public ExcelReaderException(String message)
	{
		super(message);
	}

	public ExcelReaderException(String message, Throwable cause)
	{
		super(message, cause);
	}

}
