/**
 *
 */
package com.slidetorial.excelnate.mapping.row.impl;

import static com.google.common.base.Preconditions.checkNotNull;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.Optional;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import com.slidetorial.excelnate.mapping.cell.ExcelCell;
import com.slidetorial.excelnate.mapping.cell.ExcelCellMapper;
import com.slidetorial.excelnate.mapping.cell.ExcelCellMapperException;
import com.slidetorial.excelnate.mapping.cell.ExcelSource;
import com.slidetorial.excelnate.mapping.row.ExcelObject;
import com.slidetorial.excelnate.mapping.row.ExcelRowMapper;
import com.slidetorial.excelnate.mapping.row.ExcelRowMapperException;

/**
 * This implementations uses annotations to parse Excel files and map cells into
 * fields and rows into objects. To understand the behavior of this class, refer
 * to {@link ExcelCell} and {@link ExcelObject} annotations.
 *
 * @author goobar
 * @param <T>
 *                type of the object
 *
 */
public class AnnotationExcelRowMapper<T> implements ExcelRowMapper<T>
{
	/**
	 * Creates the mapper instance for given type and source document.
	 *
	 * @param type
	 *                type of the object
	 * @param sourceDocument
	 *                a source document used by this mapper
	 * @throws NullPointerException
	 *                 if any argument is null
	 * @return the configured mapper
	 */
	public static <T> AnnotationExcelRowMapper<T> instance(Class<T> type,
		String sourceDocument) throws NullPointerException
	{
		validate(type, sourceDocument);
		return new AnnotationExcelRowMapper<>(type, sourceDocument);
	}

	/**
	 * @param type
	 * @param sourceDocument
	 */
	private static void validate(Class<?> type, String sourceDocument)
	{
		checkNotNull(type, "'type' cannot be null");
		checkNotNull(sourceDocument, "'sourceDocument' cannot be null");
	}

	private final String sourceDocument;

	private final Class<T> type;

	private AnnotationExcelRowMapper(Class<T> type, String sourceDocument)
	{
		this.type = type;
		this.sourceDocument = sourceDocument;
	}

	/* (non-Javadoc)
	 * @see com.onezerobinary.certify.excel.reader.mapping.ExcelRowMapper#map(org.apache.poi.ss.usermodel.Row)
	 */
	@Override
	public T map(Row row)
		throws NullPointerException, ExcelRowMapperException
	{
		validate(row);
		T instance = instance(row.getRowNum());
		mapCellsFromRow(instance, row, sourceDocument);
		return instance;
	}

	/**
	 * @param field
	 * @return
	 */
	private String capitalizeFieldName(Field field)
	{
		return StringUtils.capitalize(field.getName());
	}

	/**
	 * @param prefix
	 * @param field
	 * @return
	 */
	private String fieldMethodName(String prefix, Field field)
	{
		return prefix + capitalizeFieldName(field);
	}

	/**
	 * @param document
	 * @param excelCell
	 * @return
	 */
	private int getCellIndex(String sourceDocument, ExcelCell excelCell)
	{
		ExcelSource[] sources = excelCell.sources();
		Optional<ExcelSource> matchingMapping = Stream.of(sources)
			.filter(s -> sourceDocument.equals(s.source()))
			.findFirst();
		return matchingMapping.map(ExcelSource::index).orElse(-1);
	}

	/**
	 * @param cellIndex
	 * @param row
	 * @param cellMapperClass
	 * @return
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws ExcelCellMapperException
	 * @throws NullPointerException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 */
	private Object getCellValue(int cellIndex, Row row,
		Class<? extends ExcelCellMapper<?>> cellMapperClass)
		throws NullPointerException, ExcelCellMapperException,
		InstantiationException, IllegalAccessException,
		IllegalArgumentException, InvocationTargetException,
		NoSuchMethodException, SecurityException
	{
		if (cellIndex < 0)
		{
			return null;
		}
		Object cellValue;
		try
		{
			cellValue = cellMapperClass.newInstance()
				.map(row.getCell(cellIndex,
					MissingCellPolicy.CREATE_NULL_AS_BLANK));
		}
		catch (ExcelCellMapperException e)
		{
			return null;
		}
		return cellValue;
	}

	/**
	 * @param rowNum
	 * @return
	 */
	private T instance(int rowNum)
	{
		try
		{
			return type.newInstance();
		}
		catch (InstantiationException | IllegalAccessException e)
		{
			throw new ExcelRowMapperException(MessageFormat.format(
				"Failed to map row {0} to object of type {1}. Failed to instantiate an empty object.",
				rowNum, type), e);
		}
	}

	/**
	 * Maps cells from Excel row to data object.
	 *
	 * @param object
	 *                Object that contains fields annotated with
	 *                {@link ExcelCell}. This object will have all cell
	 *                fields set with values taken from the corresponding
	 *                Excel cells in the {@code row}. If object doesn't have
	 *                any cell fields, then this method does nothing.
	 * @param row
	 *                row from Excel document containing data that will be
	 *                mapped to {@code object}
	 * @param document
	 *                source document type
	 */
	private void mapCellsFromRow(Object object, Row row, String document)
	{
		Field[] fields = object.getClass().getDeclaredFields();
		for (Field field : fields)
		{
			mapField(field, object, row, document);
		}
	}

	/**
	 * @param field
	 * @param document
	 * @param row
	 */
	private void mapField(Field field, Object object, Row row,
		String document)
	{
		try
		{
			ExcelCell excelCellAnnotation = field
				.getAnnotation(ExcelCell.class);
			ExcelObject excelObjectAnnotation = field
				.getAnnotation(ExcelObject.class);
			if (excelObjectAnnotation != null)
			{
				// process data object recursively
				Object complexObjectInstance = field.getType()
					.newInstance();
				mapCellsFromRow(complexObjectInstance, row,
					document);
				setField(field, complexObjectInstance, object);
				return;
			}
			if (excelCellAnnotation == null)
			{
				return;
			}
			Object cellValue = getCellValue(
				getCellIndex(document, excelCellAnnotation),
				row, excelCellAnnotation.cellMapper());
			if (cellValue != null)
			{
				setField(field, cellValue, object);
			}
		}
		catch (InstantiationException | IllegalAccessException
			| IllegalArgumentException | InvocationTargetException
			| NoSuchMethodException | SecurityException e)
		{
			throw new ExcelRowMapperException(MessageFormat.format(
				"Mapping Excel cell to field {0} in object {1} failed.",
				field, object), e);
		}
	}

	/**
	 * @param field
	 * @param object
	 * @param value
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 *
	 */
	private void setField(Field field, Object value, Object object)
		throws NoSuchMethodException, SecurityException,
		IllegalAccessException, IllegalArgumentException,
		InvocationTargetException
	{
		setter(field, object).invoke(object, value);
	}

	/**
	 * @param field
	 * @param object
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 */
	private Method setter(Field field, Object object)
		throws NoSuchMethodException, SecurityException
	{
		return object.getClass().getMethod(
			fieldMethodName("set", field), field.getType());
	}

	/**
	 * @param row
	 */
	private void validate(Row row)
	{
		checkNotNull(row, "'row' cannot be null");
	}

}
