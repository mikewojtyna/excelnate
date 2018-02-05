/**
 *
 */
package com.slidetorial.excelnate.reader.impl;

import static com.google.common.base.Preconditions.checkNotNull;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import com.slidetorial.excelnate.mapping.row.ExcelRowMapper;
import com.slidetorial.excelnate.mapping.row.ExcelRowMapperException;
import com.slidetorial.excelnate.reader.ExcelDocument;
import com.slidetorial.excelnate.reader.ExcelReader;
import com.slidetorial.excelnate.reader.ExcelReaderException;

/**
 * A component to map all Excel rows into a collection of objects. This
 * implementation uses the {@code ApachePoi} library.
 *
 * @author goobar
 * @param <T>
 *                type of the target objects
 *
 */
public class ApachePoiExcelReader<T> implements ExcelReader<T>
{
	/**
	 * @param rowMapper
	 *                a row mapper strategy
	 * @param ignoredRows
	 *                an array of rows (indexed from 0) to be ignored
	 *                (useful e.g. to ignore the header row)
	 * @throws NullPointerException
	 *                 if any argument is null
	 * @return the instance of reader
	 */
	public static <T> ApachePoiExcelReader<T> instance(
		ExcelRowMapper<T> rowMapper, Integer... ignoredRows)
		throws NullPointerException
	{
		return new ApachePoiExcelReader<>(rowMapper, ignoredRows);
	}

	private final List<Integer> ignoredRows;

	private final ExcelRowMapper<T> rowMapper;

	/**
	 * @param rowMapper
	 *                a row mapper strategy
	 * @throws NullPointerException
	 *                 if any argument is null
	 */
	private ApachePoiExcelReader(ExcelRowMapper<T> rowMapper,
		Integer... ignoredRows) throws NullPointerException
	{
		validate(rowMapper);
		this.rowMapper = rowMapper;
		this.ignoredRows = Arrays.asList(ignoredRows);
	}

	/* (non-Javadoc)
	 * @see com.onezerobinary.certify.excel.reader.ExcelReader#read(com.onezerobinary.certify.excel.reader.ExcelDocument)
	 */
	@Override
	public List<T> read(ExcelDocument excelDocument)
		throws NullPointerException, ExcelReaderException
	{
		try (Workbook workbook = WorkbookFactory.create(
			new ByteArrayInputStream(excelDocument.getContent())))
		{
			return readWorkbook(workbook);
		}
		catch (ExcelRowMapperException | EncryptedDocumentException
			| InvalidFormatException | IOException e)
		{
			throw new ExcelReaderException(MessageFormat.format(
				"Failed to read Excel document: {0}.",
				excelDocument), e);
		}
	}

	/**
	 * @param workbook
	 * @return
	 */
	private List<T> readWorkbook(Workbook workbook)
	{
		List<T> result = new ArrayList<>();
		Sheet sheet = workbook.getSheetAt(0);
		boolean ignoreLastRow = ignoredRows.contains(-1);
		int lastRowNum = sheet.getLastRowNum();
		for (Row row : sheet)
		{
			// do not process rows which should be ignored
			if (!ignoredRows.contains(row.getRowNum()))
			{
				// -1 row number means the last row - if
				// current row is the last row and last
				// row should be ignored - do not
				// process
				if (!(ignoreLastRow
					&& row.getRowNum() == lastRowNum))
				{
					result.add(rowMapper.map(row));
				}
			}
		}
		return result;
	}

	/**
	 * @param rowMapper
	 */
	private void validate(ExcelRowMapper<T> rowMapper)
	{
		checkNotNull(rowMapper, "'rowMapper' cannot be null");
	}
}
