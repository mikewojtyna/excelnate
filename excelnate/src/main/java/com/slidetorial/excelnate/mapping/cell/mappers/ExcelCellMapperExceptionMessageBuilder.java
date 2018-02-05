/**
 *
 */
package com.slidetorial.excelnate.mapping.cell.mappers;

import org.apache.poi.ss.usermodel.Cell;

/**
 * A handy tool to prepare exception messages for Excel cell mappers.
 *
 * @author goobar
 *
 */
class ExcelCellMapperExceptionMessageBuilder
{
	/**
	 * Builds cell type not supported exception message.
	 *
	 * @param cell
	 *                a source cell
	 * @param targetType
	 *                target class type to which cell is about to be mapped
	 * @return the exception message
	 */
	static String buildExceptionCellNotSupportedMsg(Cell cell,
		Class<?> targetType)
	{
		return buildExceptionMsg(cell, targetType,
			String.format("Cell type '%s' not supported.",
				cell.getCellTypeEnum()));
	}

	/**
	 * Builds the exception message.
	 *
	 * @param cell
	 *                a source cell
	 * @param targetType
	 *                target class type to which cell is about to be mapped
	 * @param reason
	 *                the original exception
	 * @return the exception message
	 */
	static String buildExceptionMsg(Cell cell, Class<?> targetType,
		Exception reason)
	{
		return buildExceptionMsg(cell, targetType, reason.getMessage());
	}

	/**
	 * Builds the exception message.
	 *
	 * @param cell
	 *                a source cell
	 * @param targetType
	 *                target class type to which cell is about to be mapped
	 * @param reason
	 *                detailed reason message
	 * @return the exception message
	 */
	static String buildExceptionMsg(Cell cell, Class<?> targetType,
		String reason)
	{
		return String.format(
			"Failed to map cell '%s' with content '%s' to '%s'. Reason: '%s'",
			cell.getAddress(), cell, targetType.getName(), reason);
	}
}
