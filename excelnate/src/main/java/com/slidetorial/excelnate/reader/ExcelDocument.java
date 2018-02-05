/**
 *
 */
package com.slidetorial.excelnate.reader;

import static com.google.common.base.Preconditions.checkNotNull;
import java.util.Arrays;

/**
 * An abstraction over the Excel document (most often simply a file).
 *
 * @author goobar
 *
 */
public class ExcelDocument
{
	private final byte[] content;

	/**
	 * Creates the document. The content of the stream is not validated
	 * against Excel format.
	 *
	 * @param content
	 *                document content, no format validation is performed
	 * @throws NullPointerException
	 *                 if any argument is null
	 */
	public ExcelDocument(byte[] content) throws NullPointerException
	{
		validate(content);
		this.content = content;
	}

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
		ExcelDocument other = (ExcelDocument) obj;
		if (!Arrays.equals(content, other.content))
		{
			return false;
		}
		return true;
	}

	/**
	 * @return the content of this document
	 */
	public byte[] getContent()
	{
		return content;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(content);
		return result;
	}

	/**
	 * @param content
	 */
	private void validate(byte[] content)
	{
		checkNotNull(content, "'content' cannot be null");
	}
}
