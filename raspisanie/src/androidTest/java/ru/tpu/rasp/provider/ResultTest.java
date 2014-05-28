package ru.tpu.rasp.provider;

import junit.framework.TestCase;

import ru.tpu.rasp.exception.ParseException;

/**
 * @author andrey.pogrebnoy
 */
public class ResultTest extends TestCase {

	private String okString = "everything is ok!";

	public void testOkResult() throws Exception {
		Result<String> okResult = new OkResult<String>(okString);

		assertEquals(okString, okResult.get());
	}

	public void testFailResult() throws Exception {
		Result<String> failResult = new FailResult<String>(new ParseException("beda"));
		try {
			failResult.get();
			fail("fail result didn't threw an exception");
		} catch (ParseException e) {

		}
	}
}
