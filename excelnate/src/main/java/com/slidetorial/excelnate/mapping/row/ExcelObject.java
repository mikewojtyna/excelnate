/**
 *
 */
package com.slidetorial.excelnate.mapping.row;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import com.slidetorial.excelnate.mapping.cell.ExcelCell;

/**
 * Annotation to be used on complex Excel data object field. Complex Excel data
 * object is an object which can contain other complex data objects or
 * {@link ExcelCell} fields. Therefore, this annotation cannot be combined with
 * {@link ExcelCell} annotation. Data objects are processed by
 * {@link ExcelRowMapper} which initializes all Excel (data objects or cells)
 * fields. This annotation can be applied to fields only. Corresponding setter
 * must be defined in class containing annotated field or mapping will fail.
 * Complex field doesn't need to be initialized (can be null), since all object
 * fields are initialized by the mapper anyway.
 *
 * @author goobar
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ExcelObject
{

}
