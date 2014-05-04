package ru.tpu.rasp.provider;

import junit.framework.TestCase;

/**
 * @author andrey.pogrebnoy
 */
public class ResultTest extends TestCase {

	private String okString = "everything is ok!";

	public void testResult() {
		Result<String> failResult = new FailResult<String>(new Exception());
		Result<String> okResult = new OkResult<String>(okString);

		try {
			assertEquals(okString, okResult.get());
			failResult.get();
			fail("fail result didn't threw an exception");
		} catch (Exception e) {
		}

	}
}
