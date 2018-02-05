/**
 *
 */
package com.slidetorial.excelnate.mapping.cell;

import static org.assertj.core.api.Assertions.assertThat;
import org.apache.poi.ss.usermodel.Cell;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.google.common.testing.NullPointerTester;
import com.slidetorial.excelnate.mapping.cell.ExcelCellMapper;
import com.slidetorial.excelnate.mapping.cell.ExcelCellMapperException;
import com.slidetorial.excelnate.mapping.cell.mappers.ExcelCellDoubleMapper;

/**
 * @author goobar
 *
 */
@SuppressWarnings("javadoc")
public class ExcelCellDoubleMapperTest
{

	private ExcelCellMapper<Double> mapper;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		mapper = new ExcelCellDoubleMapper();
	}

	@Test
	public void should_MapCellToDouble() throws Exception
	{
		// given
		double expecteNumber = 100.99;
		Cell cell = numberCell(expecteNumber);

		// when
		double number = mapper.map(cell);

		// then
		assertThat(number).isEqualTo(expecteNumber);
	}

	@Test
	public void should_Pass_NullTests() throws Exception
	{
		NullPointerTester tester = new NullPointerTester();
		tester.testAllPublicConstructors(ExcelCellDoubleMapper.class);
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

	@Test(expected = ExcelCellMapperException.class)
	public void should_ThrowException_When_CellIsString() throws Exception
	{
		// given
		Cell cell = stringCell();

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
	 * @param number
	 * @return
	 */
	private Cell numberCell(double number)
	{
		return CellFixtureUtils.numberCell(number);
	}

	/**
	 * @return
	 */
	private Cell stringCell()
	{
		return CellFixtureUtils.stringCell("some content");
	}

}
