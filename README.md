# Excelnate

Excelnate is a library to map Excel files to Java objects.

### Example usage

Here's the basic usage of the library. The following code maps the Excel file to a list of objects. Each object corresponds to a single Excel row.


```java
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

```java
public class SimpleTestObject
{

	@ExcelCell(cellMapper = ExcelCellStringMapper.class,
		sources = { @ExcelSource(index = 0,
			source = SourceDocuments.DEFAULT_SOURCE_DOCUMENT) })
	private String stringField;	

	/**
	 * @param stringField
	 *                the stringField to set
	 */
	public void setStringField(String stringField)
	{
		this.stringField = stringField;
	}
}
```

If you want to include complex objects as fields (which themselves include @ExcelCell or @ExcelObject annotations on fields), you can use @ExcelObject annotation.
```java
public class TestNestedObject
{

	@ExcelObject
	private TestNestedObject2 complexField2;
	
	/**
	 * @param complexField2
	 *                the complexField2 to set
	 */
	public void setComplexField2(TestNestedObject2 complexField2)
	{
		this.complexField2 = complexField2;
	}
}
```
```java
public class TestNestedObject2
{

	@ExcelCell(cellMapper = ExcelCellStringMapper.class,
		sources = { @ExcelSource(index = 1,
			source = SourceDocuments.DEFAULT_SOURCE_DOCUMENT) })
	private String stringField;

	/**
	 * @param stringField
	 *                the stringField to set
	 */
	public void setStringField(String stringField)
	{
		this.stringField = stringField;
	}
}
```

It's important to remember that annotated classes must have setters for each mapped field.

### More examples

Check tests (especially inside the ```reader``` package) to see more examples.

### Cell mappers

The library is designed to easily create your own cell mappers. To create a custom cell mapper, simply implement ExcelCellMapper interface and point to the new mapper inside @ExcelCell annotation using cellMapper argument.

```java
@ExcelCell(cellMapper = MyCustomMapper.class,
		sources = { @ExcelSource(index = 0,
			source = SourceDocuments.DEFAULT_SOURCE_DOCUMENT) })
private MyClass stringField;
```
```java	
public class MyCustomMapper implements ExcelCellMapper<MyClass> 
{

	@Override
	public MyClass map(Cell cell)
		throws NullPointerException, ExcelCellMapperException
	{
		// TODO: implement your custom mapping
	}
}
```