/**
 *
 */
package com.slidetorial.excelnate.mapping.cell;

import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDate;
import org.apache.poi.ss.usermodel.Cell;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.google.common.testing.NullPointerTester;
import com.slidetorial.excelnate.mapping.cell.ExcelCellMapperException;
import com.slidetorial.excelnate.mapping.cell.mappers.ExcelCellLocalDateMapper;

/**
 * @author goobar
 *
 */
@SuppressWarnings("javadoc")
public class ExcelCellLocalDateMapperTest
{

	private ExcelCellLocalDateMapper mapper;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		mapper = new ExcelCellLocalDateMapper();
	}

	@Test
	public void should_MapCellToLocalDate() throws Exception
	{
		// given
		LocalDate expectedDate = LocalDate.of(2017, 1, 13);
		Cell cell = dateCell(2017, 1, 13);

		// when
		LocalDate date = mapper.map(cell);

		// then
		assertThat(date).isEqualTo(expectedDate);
	}

	@Test
	public void should_Pass_NullTests() throws Exception
	{
		NullPointerTester tester = new NullPointerTester();
		tester.testAllPublicConstructors(
			ExcelCellLocalDateMapper.class);
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
	public void should_ThrowException_When_CellIsNotNumeric()
		throws Exception
	{
		// given
		Cell cell = notNumericCell();

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
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	private Cell dateCell(int year, int month, int day)
	{
		return CellFixtureUtils.dateCell(year, month, day);
	}

	/**
	 * @return
	 *
	 */
	private Cell notNumericCell()
	{
		return stringCell();
	}

	/**
	 * @return
	 */
	private Cell stringCell()
	{
		return CellFixtureUtils.stringCell("string cell content");
	}

}
