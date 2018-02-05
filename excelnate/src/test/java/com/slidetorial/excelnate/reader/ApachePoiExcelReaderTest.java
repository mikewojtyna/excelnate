/**
 *
 */
package com.slidetorial.excelnate.reader;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.slidetorial.excelnate.mapping.row.ExcelRowMapper;
import com.slidetorial.excelnate.mapping.row.SourceDocuments;
import com.slidetorial.excelnate.mapping.row.impl.AnnotationExcelRowMapper;
import com.slidetorial.excelnate.reader.impl.ApachePoiExcelReader;
import com.slidetorial.excelnate.reader.testobjects.SimpleTestObject;

/**
 * @author goobar
 *
 */
@SuppressWarnings("javadoc")
public class ApachePoiExcelReaderTest
{

	private ExcelRowMapper<SimpleTestObject> rowMapper;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		rowMapper = AnnotationExcelRowMapper.instance(
			SimpleTestObject.class,
			SourceDocuments.DEFAULT_SOURCE_DOCUMENT);
	}

	@Test
	public void should_MapFirstDataRow() throws Exception
	{
		// given
		// file contains 6 rows, 1st row being the header, rest rows
		// being data rows - refer to the file "test-objects.xlsx" to
		// see the values
		ExcelDocument excelDocument = ExcelDocumentFixtureUtils
			.testObjectDocument();
		// create reader which will ignore only first (index 0) header
		// row
		ApachePoiExcelReader<SimpleTestObject> reader = ApachePoiExcelReader
			.instance(rowMapper, 0);
		// first data row object
		SimpleTestObject testObject1 = new SimpleTestObject();
		testObject1.setStringField("string1");

		// when
		List<SimpleTestObject> objects = reader.read(excelDocument);

		// then
		// 1st row (header) should be ignored
		assertThat(objects).hasSize(5);
		assertThat(objects.get(0)).isEqualTo(testObject1);
	}

	@Test
	public void should_NotMapIgnoredRows() throws Exception
	{
		// given
		// file contains 6 rows, 1st row being the header, rest rows
		// being data rows - refer to the file "test-objects.xlsx" to
		// see the values
		ExcelDocument excelDocument = ExcelDocumentFixtureUtils
			.testObjectDocument();
		// create reader which will ignore 1st (index 0), 4th (index 3)
		// and fifth (index 4) rows
		ApachePoiExcelReader<SimpleTestObject> reader = ApachePoiExcelReader
			.instance(rowMapper, 0, 3, 4);
		// these objects should be mapped
		SimpleTestObject testObject1 = new SimpleTestObject();
		testObject1.setStringField("string1");
		SimpleTestObject testObject2 = new SimpleTestObject();
		testObject2.setStringField("string2");
		SimpleTestObject testObject5 = new SimpleTestObject();
		testObject5.setStringField("string5");

		// when
		List<SimpleTestObject> objects = reader.read(excelDocument);

		// then
		// 1st, 4th and 5th rows should be ignored
		assertThat(objects).hasSize(3);
		assertThat(objects).containsExactly(testObject1, testObject2,
			testObject5);
	}

	@Test
	public void should_NotMapLastRow_When_Ignored() throws Exception
	{
		// given
		// file contains 6 rows, 1st row being the header, rest rows
		// being data rows - refer to the file "test-objects.xlsx" to
		// see the values
		ExcelDocument excelDocument = ExcelDocumentFixtureUtils
			.testObjectDocument();
		// create reader which will ignore the last row with special
		// meaning (-1)
		ApachePoiExcelReader<SimpleTestObject> reader = ApachePoiExcelReader
			.instance(rowMapper, -1);
		// these objects should be mapped
		SimpleTestObject testObject5 = new SimpleTestObject();
		testObject5.setStringField("string5");

		// when
		List<SimpleTestObject> objects = reader.read(excelDocument);

		// then
		// only the last sixth row should be ignored
		assertThat(objects).hasSize(5);
		assertThat(objects).doesNotContain(testObject5);
	}

	@Test
	public void should_ThrowException_When_ExcelFileIsInvalid()
		throws Exception
	{
		// given
		ApachePoiExcelReader<SimpleTestObject> reader = ApachePoiExcelReader
			.instance(rowMapper);

		// when
		assertThatThrownBy(() -> reader.read(invalidDocument()))
			// then
			.isInstanceOf(ExcelReaderException.class);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception
	{
	}

	/**
	 * @return
	 */
	private ExcelDocument invalidDocument()
	{
		return new ExcelDocument("invalid Excel file".getBytes());
	}

}
