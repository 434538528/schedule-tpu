package ru.tpu.rasp.provider;

import junit.framework.TestCase;

/**
 * @author andrey.pogrebnoy
 */
public class ResultTest extends TestCase {

	private String okString = "everything is ok!";

	public void testResult() {
		Result<String> failResult = new Result<String>(new MockProcessor(true));
		Result<String> okResult = new Result<String>(new MockProcessor(false));

		try {
			assertEquals(okString, okResult.get());
			failResult.get();
			fail("fail result didn't threw an exception");
		} catch (Exception e) {
		}

	}

	private class MockProcessor implements Result.Processor<String> {

		private final boolean wouldFail;

		public MockProcessor(boolean wouldFail) {
			this.wouldFail = wouldFail;
		}

		@Override
		public String process() throws Exception {
			if (wouldFail) {
				throw new Exception("mock exception");
			}
			return okString;
		}
	}
}
