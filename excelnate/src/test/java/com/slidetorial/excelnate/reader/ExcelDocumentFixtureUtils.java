/**
 *
 */
package com.slidetorial.excelnate.reader;

import java.io.IOException;
import java.io.InputStream;
import org.apache.poi.util.IOUtils;
import com.slidetorial.excelnate.testobjects.TestObject;

/**
 * @author goobar
 *
 */
public class ExcelDocumentFixtureUtils
{

	private static final String TEST_OBJECT_FILE = "test-objects.xlsx";

	/**
	 * @return the Excel document containing rows to be mapped into
	 *         {@link TestObject}
	 */
	public static ExcelDocument testObjectDocument()
	{
		return new ExcelDocument(peopleDocumentContent());
	}

	/**
	 * @param filename
	 * @return
	 */
	private static byte[] documentContent(String filename)
	{
		try (InputStream stream = fileStream(filename))
		{
			return IOUtils.toByteArray(stream);
		}
		catch (IOException e)
		{
			throw new RuntimeException(e);
		}
	}

	/**
	 * @param filename
	 * @return
	 */
	private static InputStream fileStream(String filename)
	{
		return ExcelDocumentFixtureUtils.class.getClassLoader()
			.getResourceAsStream("excel/" + filename);
	}

	/**
	 * @return
	 */
	private static byte[] peopleDocumentContent()
	{
		return documentContent(TEST_OBJECT_FILE);
	}

}
