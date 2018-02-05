/**
 *
 */
package com.slidetorial.excelnate.reader;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.google.common.testing.NullPointerTester;
import nl.jqno.equalsverifier.EqualsVerifier;

/**
 * @author goobar
 *
 */
@SuppressWarnings("javadoc")
public class ExcelDocumentTest
{

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
	}

	@Test
	public void should_CreateInstance() throws Exception
	{
		// given
		byte[] content = content();

		// when
		ExcelDocument excelDocument = new ExcelDocument(content);

		// then
		assertThat(excelDocument.getContent()).isEqualTo(content);
	}

	@Test
	public void should_Pass_EqualsTests() throws Exception
	{
		EqualsVerifier.forClass(ExcelDocument.class).usingGetClass()
			.verify();
	}

	@Test
	public void should_Pass_NullTests() throws Exception
	{
		NullPointerTester tester = new NullPointerTester();
		tester.testAllPublicConstructors(ExcelDocument.class);
		tester.testAllPublicInstanceMethods(instance());
	}

	@Test
	public void should_ToString_ReturnNonEmptyString() throws Exception
	{
		assertFalse(instance().toString().isEmpty());
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
	private byte[] content()
	{
		return "fake content".getBytes();
	}

	/**
	 * @return
	 */
	private ExcelDocument instance()
	{
		return new ExcelDocument(content());
	}

}
