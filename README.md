# Excelnate

Excelnate is a library to map Excel files to Java objects.

### Example usage

Here's the basic usage of the library. The following code maps the Excel file to a list of objects. Each object corresponds to a single Excel row.


```
	@Test
	public void should_MapFirstDataRow() throws Exception
	{
		// given
		// file contains 6 rows, 1st row being the header, rest rows
		// being data rows - refer to the file "test-objects.xlsx" to
		// see the values
		ExcelDocument excelDocument = ExcelDocumentFixtureUtils
			.testObjectDocument();
		// create reader which will ignore only first (index 0) header
		// row
		ExcelReader<SimpleTestObject> reader = ApachePoiExcelReader
			.instance(rowMapper, 0);
		// first data row object
		SimpleTestObject testObject1 = new SimpleTestObject();
		testObject1.setStringField("string1");

		// when
		List<SimpleTestObject> objects = reader.read(excelDocument);

		// then
		// 1st row (header) should be ignored
		assertThat(objects).hasSize(5);
		assertThat(objects.get(0)).isEqualTo(testObject1);
	}
```

SimpleTestObject in the example includes Excelnate annotations to map cells into fields.

```
public class SimpleTestObject
{
	@ExcelCell(cellMapper = ExcelCellStringMapper.class,
		sources = { @ExcelSource(index = 0,
			source = SourceDocuments.DEFAULT_SOURCE_DOCUMENT) })
	private String stringField;
}
```