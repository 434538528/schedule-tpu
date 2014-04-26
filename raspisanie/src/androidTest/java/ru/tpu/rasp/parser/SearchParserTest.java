package ru.tpu.rasp.parser;

import junit.framework.TestCase;

import ru.tpu.rasp.TestUtils;

/**
 * @author andrey.pogrebnoy
 */
public class SearchParserTest extends TestCase {

	private SearchParser mParser;
	private String[] expectedArray = new String[]{"key1", "key2", "key3"};

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mParser = new SearchParser();
	}

	public void testSearchParser() throws Exception {
		String[] parsedArray = mParser.parse(TestUtils.wrapGrabberJSON(SEARCH_JSON));
		assertEquals(expectedArray.length, parsedArray.length);
		for (int i = 0; i < expectedArray.length; i++) {
			assertEquals(expectedArray[i], parsedArray[i]);
		}
	}

	private static final String SEARCH_JSON = "{keys:['key1', 'key2', 'key3']}";
}
