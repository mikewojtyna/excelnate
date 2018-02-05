/**
 *
 */
package com.slidetorial.excelnate.mapping.cell;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation is used by {@link ExcelCell} annotation reader to determine
 * cell mapping for different source documents.
 *
 * @author goobar
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({})
public @interface ExcelSource
{
	/**
	 * Index of the cell in the current row of the Excel source document.
	 * The content of the cell will be mapped to the annotated field.
	 *
	 * @return index of the cell
	 */
	int index();

	/**
	 * A unique name of the source document. This name must be unique inside
	 * the {@link ExcelCell } annotation. Otherwise, behavior is undefined.
	 */
	String source();
}
