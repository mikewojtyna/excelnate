/**
 *
 */
package com.slidetorial.excelnate.mapping.cell;

import static org.assertj.core.api.Assertions.assertThat;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.google.common.testing.NullPointerTester;
import com.slidetorial.excelnate.mapping.cell.mappers.ExcelCellStringMapper;

/**
 * @author goobar
 *
 */
@SuppressWarnings("javadoc")
public class ExcelCellStringMapperTest
{

	private ExcelCellStringMapper mapper;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		mapper = new ExcelCellStringMapper();
	}

	@Test
	public void should_MapCellToString() throws Exception
	{
		// given
		Cell cell = cell("  cell content ");

		// when
		String cellStringValue = mapper.map(cell);

		// then
		assertThat(cellStringValue).isEqualTo("cell content");
	}

	@Test
	public void should_MapNumberCellToString() throws Exception
	{
		// given
		Cell cell = numberCell(80057);
		String expectedResult = "80057";

		// when
		String result = mapper.map(cell);

		// then
		assertThat(result).isEqualTo(expectedResult);
	}

	@Test
	public void should_Pass_NullTests() throws Exception
	{
		NullPointerTester tester = new NullPointerTester();
		tester.testAllPublicConstructors(ExcelCellStringMapper.class);
		tester.testAllPublicInstanceMethods(mapper);
	}

	@Test(expected = ExcelCellMapperException.class)
	public void should_ThrowException_When_CellIsBlank() throws Exception
	{
		// given
		Cell cell = blankCell();

		// when
		mapper.map(cell);
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
	private Cell blankCell()
	{
		return CellFixtureUtils.blankCell();
	}

	/**
	 * @param content
	 * @return
	 * @throws IOException
	 */
	private Cell cell(String content)
	{
		return CellFixtureUtils.stringCell(content);
	}

	/**
	 * @param value
	 * @return
	 * @throws IOException
	 */
	private Cell numberCell(double value)
	{
		return CellFixtureUtils.numberCell(value);
	}

}
