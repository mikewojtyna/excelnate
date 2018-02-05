/**
 *
 */
package com.slidetorial.excelnate.reader;

import java.util.List;

/**
 * A generic interface to read Excel documents as objects. This is the interface
 * which should be used directly by users wanting to convert Excel documents
 * into list of objects.
 *
 * @author goobar
 * @param <T>
 *                the result object type
 *
 */
public interface ExcelReader<T>
{
	/**
	 * Deserializes an Excel document into list of objects.
	 *
	 * @param excelDocument
	 *                the Excel document
	 * @return deserialized list of objects, ordering is not guaranteed
	 * @throws NullPointerException
	 *                 if any argument is null
	 * @throws ExcelReaderException
	 *                 if reader fails
	 */
	List<T> read(ExcelDocument excelDocument)
		throws NullPointerException, ExcelReaderException;
}
