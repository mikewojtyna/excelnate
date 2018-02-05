/**
 *
 */
package com.slidetorial.excelnate.mapping.cell;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import com.slidetorial.excelnate.mapping.cell.mappers.ExcelCellStringMapper;

/**
 * This annotation is a part of the Excel mapping system. One should use it to
 * map Excel document cell into single value field of the data mapping class.
 * This annotation is applicable only to simple fields, which can be created
 * directly from single Excel cell. The corresponding setter method (according
 * to Bean specification) is required to set the value of the field. By default,
 * mapping from cell to field assumes the type of the underlying field is
 * {@code String}. An instance of {@link ExcelCellMapper} can be provided to map
 * cell into arbitrary class.
 *
 * @author goobar
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.FIELD)
public @interface ExcelCell
{
	/**
	 * The mapping strategy used to create target data class from content of
	 * the Excel cell.
	 *
	 * @return the cell mapping strategy
	 */
	Class<? extends ExcelCellMapper<?>> cellMapper() default ExcelCellStringMapper.class;

	/**
	 * Collection of source document mappings. At least one
	 * {@link ExcelSource} is required to map cell to field. Multiple excel
	 * sources might be useful when the same field can be mapped from
	 * multiple documents. For example, field {@code field} of class
	 * {@code TestObject} can be mapped both from documents {@code doc0} and
	 * {@code doc1}. However, it's possible that cells/columns containing
	 * the desired cell value are indexed differently. For example: in
	 * {@code doc1} at index {@code 0}, and in {@code doc2} at index
	 * {@code 1}. In such case, you can use the following snippet
	 *
	 * <pre>
	 * public class TestObject
	 * {
	 * 	&#64;ExcelCell(cellMapper = ExcelCellStringMapper.class,
	 * 		sources = { &#64;ExcelSource(index = 0, source = "doc0"),
	 * 			&#64;ExcelSource(index = 1, source = "doc1") })
	 * 	private String field;
	 * }
	 * </pre>
	 *
	 * to map the same field using different cell indexes from two different
	 * documents. Note that {@code source} argument of {@link ExcelSource}
	 * is just a string label used to differentiate cell mapping for
	 * different documents. This label is passed to the row mapper, which is
	 * usually configured to work only with a single source document.
	 *
	 * @return the array of all available mappings
	 */
	ExcelSource[] sources() default {};
}
