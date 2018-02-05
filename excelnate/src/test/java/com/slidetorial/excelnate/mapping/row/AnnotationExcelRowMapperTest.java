/**
 *
 */
package com.slidetorial.excelnate.mapping.row;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Calendar;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.google.common.testing.NullPointerTester;
import com.slidetorial.excelnate.mapping.row.impl.AnnotationExcelRowMapper;
import com.slidetorial.excelnate.mapping.row.testobjects.TestAbstractClass;
import com.slidetorial.excelnate.mapping.row.testobjects.TestInterface;
import com.slidetorial.excelnate.mapping.row.testobjects.TestObject;
import com.slidetorial.excelnate.mapping.row.testobjects.TestObject2;
import com.slidetorial.excelnate.mapping.row.testobjects.TestObject3;
import com.slidetorial.excelnate.mapping.row.testobjects.TestObject4;
import com.slidetorial.excelnate.mapping.row.testobjects.TestObject5;

/**
 * @author goobar
 *
 */
@SuppressWarnings("javadoc")
public class AnnotationExcelRowMapperTest
{

	private static final String DEFAULT_SOURCE_DOCUMENT = SourceDocuments.DEFAULT_SOURCE_DOCUMENT;

	/**
	 * @return
	 */
	private static LocalDate randomDate()
	{
		return LocalDate.ofEpochDay(RandomUtils.nextInt(0, 800_000));
	}

	/**
	 * @return
	 */
	private static Workbook workbook()
	{
		return new XSSFWorkbook();
	}

	private AnnotationExcelRowMapper<TestObject> mapper;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		mapper = AnnotationExcelRowMapper.instance(TestObject.class,
			DEFAULT_SOURCE_DOCUMENT);
	}

	@Test
	public void should_CreateInstanceOf_TestObject() throws Exception
	{
		// given
		Row row = emptyRow();

		// when
		TestObject object = mapper.map(row);

		// then
		assertThat(object).isNotNull();
	}

	@Test
	public void should_MapComplexField() throws Exception
	{
		// given
		String complexFieldString = randomString();
		String complexField2String = randomString();
		// cell indexes reflect field mapping annotations of test object
		Row row = rowBuilder().withCell(0, complexFieldString)
			.withCell(1, complexField2String).build();

		// when
		TestObject object = mapper.map(row);

		// then
		assertThat(object.getComplexField().getStringField())
			.isEqualTo(complexFieldString);
		assertThat(object.getComplexField().getComplexField2()
			.getStringField()).isEqualTo(complexField2String);
	}

	@Test
	public void should_MapMultipleCells() throws Exception
	{
		// given
		String stringField = randomString();
		LocalDate dateField = randomDate();
		double doubleField = randomDouble();
		// cell indexes reflect field mapping annotations of test object
		Row row = rowBuilder().withCell(0, stringField)
			.withCell(1, dateField).withCell(2, doubleField)
			.build();

		// when
		TestObject object = mapper.map(row);

		// then
		assertThat(object.getStringField()).isEqualTo(stringField);
		assertThat(object.getDateField()).isEqualTo(dateField);
		assertThat(object.getDoubleField()).isEqualTo(doubleField);
	}

	@Test
	public void should_MapNonSequentialCells() throws Exception
	{
		// given
		String stringField = randomString();
		LocalDate dateField = randomDate();
		double doubleField = randomDouble();
		// cell indexes reflect field mapping annotations of test object
		Row row = rowBuilder().withCell(10, stringField)
			.withCell(37, dateField).withCell(250, doubleField)
			.build();

		// when
		TestObject object = mapper.map(row);

		// then
		assertThat(object.getSecondStringField())
			.isEqualTo(stringField);
		assertThat(object.getSecondDateField()).isEqualTo(dateField);
		assertThat(object.getSecondDoubleField())
			.isEqualTo(doubleField);
	}

	@Test
	public void should_MapSingleCell() throws Exception
	{
		// given
		String cellValue = RandomStringUtils.randomAlphabetic(10);
		// single cell (0 indexed) row
		Row row = singleCellRow(cellValue);

		// when
		TestObject object = mapper.map(row);

		// then
		assertThat(object.getStringField()).isEqualTo(cellValue);
	}

	@Test
	public void should_MapTheSameField_From_TwoSources() throws Exception
	{
		// given
		// two mappers taking values from two different source documents
		ExcelRowMapper<TestObject> doc0Mapper = AnnotationExcelRowMapper
			.instance(TestObject.class,
				TestObject.SOURCE_DOCUMENT_0);
		ExcelRowMapper<TestObject> doc1Mapper = AnnotationExcelRowMapper
			.instance(TestObject.class,
				TestObject.SOURCE_DOCUMENT_1);
		// cell indexes reflect field mapping annotations of test object
		String doc0StringField = randomString();
		String doc1StringField = randomString();
		Row doc0Row = rowBuilder().withCell(15, doc0StringField)
			.build();
		Row doc1Row = rowBuilder().withCell(20, doc1StringField)
			.build();

		// when
		TestObject doc0Object = doc0Mapper.map(doc0Row);
		TestObject doc1Object = doc1Mapper.map(doc1Row);

		// then
		assertThat(doc0Object.getStringField())
			.isEqualTo(doc0StringField);
		assertThat(doc1Object.getStringField())
			.isEqualTo(doc1StringField);
	}

	@Test
	public void should_NotMapCell_When_CellIsEmpty() throws Exception
	{
		// given
		Row row = emptyRow();

		// when
		TestObject object = mapper.map(row);

		// then
		assertThat(object.getStringField()).isNull();
	}

	@Test
	public void should_Pass_NullTests() throws Exception
	{
		NullPointerTester tester = new NullPointerTester();
		tester.testAllPublicConstructors(
			AnnotationExcelRowMapper.class);
		tester.testAllPublicInstanceMethods(mapper);
		tester.testAllPublicStaticMethods(
			AnnotationExcelRowMapper.class);
	}

	@Test
	public void should_ThrowException_When_ComplexFieldHasNoSetter()
		throws Exception
	{
		// given
		ExcelRowMapper<TestObject3> testObject3Mapper = AnnotationExcelRowMapper
			.instance(TestObject3.class, DEFAULT_SOURCE_DOCUMENT);
		Row row = emptyRow();

		// when
		assertThatThrownBy(() -> testObject3Mapper.map(row))
			// then
			.isInstanceOf(ExcelRowMapperException.class);
	}

	@Test
	public void should_ThrowException_When_ComplexFieldIsAbstractClass()
		throws Exception
	{
		// given
		ExcelRowMapper<TestObject5> testObject5Mapper = AnnotationExcelRowMapper
			.instance(TestObject5.class, DEFAULT_SOURCE_DOCUMENT);
		Row row = singleCellRow(randomString());

		// when
		assertThatThrownBy(() -> testObject5Mapper.map(row))
			// then
			.isInstanceOf(ExcelRowMapperException.class);
	}

	@Test
	public void should_ThrowException_When_ComplexFieldIsInterface()
		throws Exception
	{
		// given
		ExcelRowMapper<TestObject4> testObject4Mapper = AnnotationExcelRowMapper
			.instance(TestObject4.class, DEFAULT_SOURCE_DOCUMENT);
		Row row = singleCellRow(randomString());

		// when
		assertThatThrownBy(() -> testObject4Mapper.map(row))
			// then
			.isInstanceOf(ExcelRowMapperException.class);
	}

	@Test
	public void should_ThrowException_When_FieldHasNoSetter()
		throws Exception
	{
		// given
		ExcelRowMapper<TestObject2> testObject2Mapper = AnnotationExcelRowMapper
			.instance(TestObject2.class, DEFAULT_SOURCE_DOCUMENT);
		Row row = singleCellRow(randomString());

		// when
		assertThatThrownBy(() -> testObject2Mapper.map(row))
			// then
			.isInstanceOf(ExcelRowMapperException.class);
	}

	@Test
	public void should_ThrowException_When_TypeIsAbstract() throws Exception
	{
		// given
		ExcelRowMapper<TestAbstractClass> abstractMapper = AnnotationExcelRowMapper
			.instance(TestAbstractClass.class,
				DEFAULT_SOURCE_DOCUMENT);
		Row row = singleCellRow(randomString());

		// when
		assertThatThrownBy(() -> abstractMapper.map(row))
			// then
			.isInstanceOf(ExcelRowMapperException.class);
	}

	@Test
	public void should_ThrowException_When_TypeIsInterface()
		throws Exception
	{
		// given
		ExcelRowMapper<TestInterface> interfaceMapper = AnnotationExcelRowMapper
			.instance(TestInterface.class, DEFAULT_SOURCE_DOCUMENT);
		Row row = singleCellRow(randomString());

		// when
		assertThatThrownBy(() -> interfaceMapper.map(row))
			// then
			.isInstanceOf(ExcelRowMapperException.class);
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
	 * @throws IOException
	 */
	private Row emptyRow() throws IOException
	{
		try (Workbook workbook = workbook())
		{
			return workbook.createSheet().createRow(0);
		}
	}

	/**
	 * @return
	 */
	private double randomDouble()
	{
		return RandomUtils.nextDouble();
	}

	/**
	 * @return
	 */
	private String randomString()
	{
		return RandomStringUtils.randomAlphabetic(10);
	}

	/**
	 * @return
	 */
	private RowBuilder rowBuilder()
	{
		return RowBuilder.instance();
	}

	/**
	 * @param cellValue
	 * @return
	 * @throws IOException
	 */
	private Row singleCellRow(String cellValue) throws IOException
	{
		try (Workbook workbook = workbook())
		{
			Row row = workbook.createSheet().createRow(0);
			Cell cell = row.createCell(0);
			cell.setCellType(CellType.STRING);
			cell.setCellValue(cellValue);
			return row;
		}
	}

	private static class RowBuilder
	{
		static RowBuilder instance()
		{
			return new RowBuilder();
		}

		private final Row row;

		private final Workbook workbook;

		/**
		 *
		 */
		private RowBuilder()
		{
			workbook = workbook();
			row = workbook.createSheet().createRow(0);
		}

		/**
		 * @param index
		 * @param value
		 * @return
		 */
		public RowBuilder withCell(int index, double value)
		{
			Cell cell = row.createCell(index, CellType.NUMERIC);
			cell.setCellValue(value);
			return this;
		}

		/**
		 * @param index
		 * @param value
		 * @return
		 */
		public RowBuilder withCell(int index, LocalDate value)
		{
			Calendar calendar = Calendar.getInstance();
			calendar.set(value.getYear(), value.getMonthValue() - 1,
				value.getDayOfMonth());
			Cell cell = row.createCell(index, CellType.NUMERIC);
			cell.setCellValue(calendar.getTime());
			return this;
		}

		Row build() throws IOException
		{
			try
			{
				return row;
			}
			finally
			{
				workbook.close();
			}
		}

		RowBuilder withCell(int index, String value)
		{
			Cell cell = row.createCell(index, CellType.STRING);
			cell.setCellValue(value);
			return this;
		}
	}

}
